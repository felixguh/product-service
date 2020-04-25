package br.com.productservice.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.productservice.exception.ProductNotExistsException;

@RestControllerAdvice
public class ExceptionHandlerController {
	
    @ExceptionHandler(ProductNotExistsException.class)
    public ResponseEntity<Object> handleProductNotExistsException() {
        return ResponseEntity.noContent().build();
    }

}
