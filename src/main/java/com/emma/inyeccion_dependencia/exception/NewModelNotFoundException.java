package com.emma.inyeccion_dependencia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;

public class NewModelNotFoundException extends ErrorResponseException {

    public NewModelNotFoundException(String message){
        super(HttpStatus.NOT_FOUND, asProblemDetail(message),null);
    }

    public static ProblemDetail asProblemDetail(String message){
        ProblemDetail problemDetail= ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,message);
        problemDetail.setTitle("Model not found");
//        problemDetail.setType(URI.create("/not found"));
        problemDetail.setProperty("new value", "value test");
        problemDetail.setProperty("other:","other test");

        return problemDetail;
    }
}
