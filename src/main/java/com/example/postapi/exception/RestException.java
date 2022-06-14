package com.example.postapi.exception;

import lombok.Getter;

@Getter
public class RestException extends RuntimeException {


    public RestException(String message) {
        super(message);

    }
}