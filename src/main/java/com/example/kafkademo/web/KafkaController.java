package com.example.kafkademo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: stevedavis
 * Date: 28/03/2018
 * Time: 18:28
 * Description:
 */
@RestController
@RequestMapping("/api")
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka-demo.ok-queue:phooey}")
    private String okQueue;

    @RequestMapping("/doIt/{number}")
    public void doIt(@PathVariable("number") int number) {

        kafkaTemplate.send(okQueue, "" + number);

    }

}
