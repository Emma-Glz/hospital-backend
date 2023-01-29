package com.emma.inyeccion_dependencia.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


//intersectartodo el tipo de excepcionesnen exceptionHAndler
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {//clase que permite manejar todas las

    // expciones personalizadas o normales del proyecto
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handlerModelNotFoundException(ModelNotFoundException ex, WebRequest request) {
        CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handlerAllException(ModelNotFoundException ex, WebRequest request) {
        CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String msj = ex.getBindingResult().getAllErrors().stream().map(
               e -> e.getCode().concat(":").concat(e.getDefaultMessage())
        ).collect(Collectors.joining());

        CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(), msj, request.getDescription(false));
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }
}
