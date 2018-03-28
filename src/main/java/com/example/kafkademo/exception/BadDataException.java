package com.example.kafkademo.exception;

import lombok.Data;

/**
 * User: stevedavis
 * Date: 28/03/2018
 * Time: 18:45
 * Descriptio
 * n:
 */
@Data
public class BadDataException extends Exception {

    private String data;

    public BadDataException(String data) {

        this.data = data;

    }

}
