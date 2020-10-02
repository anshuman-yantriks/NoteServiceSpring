package com.example.exception;

import com.example.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class UserGlobalExceptionHandler {
    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<Response>> webExchangeBindExceptionHandler(Exception exception){
        String errorMessage="";
        if(exception instanceof WebExchangeBindException){
            errorMessage = ((WebExchangeBindException) exception).getAllErrors().get(0).getDefaultMessage();
        }
        Response response = new Response(400,errorMessage,null);
        return Mono.just(ResponseEntity.ok(response));
    }

    @ExceptionHandler(NoteException.class)
    public Mono<ResponseEntity<Response>> userExceptionHandler(Exception exception){
        Response response = new Response(401,exception.getMessage(), null);
        return  Mono.just(new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST));
    }

}
