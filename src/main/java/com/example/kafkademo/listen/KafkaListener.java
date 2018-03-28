package com.example.kafkademo.listen;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.kafkademo.exception.BadDataException;
import lombok.extern.slf4j.Slf4j;

/**
 * User: stevedavis
 * Date: 28/03/2018
 * Time: 18:26
 * Description:
 */
@Slf4j
@Component
public class KafkaListener {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka-demo.error-queue:phooey}")
    private String errorQueue;

    @org.springframework.kafka.annotation.KafkaListener(topics = "#{'${kafka-demo.ok-queue:phooey}'}")
    public void receive(ConsumerRecord<?, ?> cr) throws Exception {

        try {

            log.info(">> " + cr.toString());
            log.info("  >> " + cr.value().toString());

            if ("666".equalsIgnoreCase(cr.value().toString())) {

                throw new BadDataException(cr.value().toString());

            }

        } catch (BadDataException bde) {

            // Oops

            log.error("Error>> " + bde.getData());
            kafkaTemplate.send(errorQueue, bde.getData());

        } catch (Exception e) {

            // eat the message

        }

    }
}
