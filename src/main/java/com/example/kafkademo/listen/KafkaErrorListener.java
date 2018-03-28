package com.example.kafkademo.listen;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * User: stevedavis
 * Date: 28/03/2018
 * Time: 18:48
 * Description:
 */
@Slf4j
@Component
public class KafkaErrorListener {

    @org.springframework.kafka.annotation.KafkaListener(topics = "#{'${kafka-demo.error-queue:phooey2}'}")
    public void receive(ConsumerRecord<?, ?> cr) throws Exception {

        try {

            log.info("Manage Error>> " + cr.toString());

        } catch (Exception e) {

            // eat the message
            throw new Exception("aaagghh");

        }

    }

}
