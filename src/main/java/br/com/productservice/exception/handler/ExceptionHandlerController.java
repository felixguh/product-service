package br.com.productservice.exception.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.productservice.common.model.ErrorResponse;
import br.com.productservice.exception.ProductNotExistsException;

@RestControllerAdvice
public class ExceptionHandlerController {
	
    private final MessageSource messageSource;

    public ExceptionHandlerController(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }
	
    @ExceptionHandler(ProductNotExistsException.class)
    public ResponseEntity<Object> handleProductNotExistsException() {
        return ResponseEntity.noContent().build();
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = BAD_REQUEST)
    public Collection<ErrorResponse> handleMethodArgumentNotValidExceptionInContext(final MethodArgumentNotValidException ex) {
        final var errors = new ArrayList<ErrorResponse>();

        errors.addAll(ex.getBindingResult().getGlobalErrors().stream().map(violation -> ErrorResponse.as(message(violation)))
                .collect(Collectors.toList()));

        errors.addAll(ex.getBindingResult().getFieldErrors().stream()
                .map(violation -> ErrorResponse.as(message(violation)).tag(violation.getField())).collect(Collectors.toList()));

        return errors;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(code = BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadableException(final HttpMessageNotReadableException ex) {
        if (ex.getRootCause() instanceof JsonMappingException) {
            return handleJsonMappingException((JsonMappingException) ex.getRootCause());
        }

        return ErrorResponse.as(message("message.messageNotReadable"));
    }

    private ErrorResponse handleJsonMappingException(final JsonMappingException ex) {
        final String paramName = ex.getPath().stream()
                .map(ref -> Optional.ofNullable(ref.getFieldName()).orElse(String.format("[%s]", ref.getIndex())))
                .collect(Collectors.joining("."));

        return ErrorResponse
                .as(messageSource.getMessage("message.invalidParameterValue", new Object[] { paramName }, LocaleContextHolder.getLocale()))
                .tag(paramName);
    }
    
    private String message(final MessageSourceResolvable violation) {
        return messageSource.getMessage(violation, LocaleContextHolder.getLocale());
    }

    private String message(final String code, final Object... params) {
        return messageSource.getMessage(code, params, LocaleContextHolder.getLocale());
    }


}
