package com.telefonica.msqueryinternalqualification.exception;

import com.telefonica.msqueryinternalqualification.model.dto.ResponseDTO;
import com.telefonica.msqueryinternalqualification.model.enums.EResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.text.ParseException;

/**
 * @version 2021-06-10
 * @author dpanquev
 * */
@ControllerAdvice
@Slf4j
public class ExceptionsHandler {

    /**
     * Method obtain error for NotContentException
     * @param e
     * @return
     * */
    @ExceptionHandler(NotContentException.class)
    public ResponseEntity<Object> notContentException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDTO<>(null, EResponseType.NO_CONTENT, e.getMessage(), "204"));
    }

    /**
     * Method obtain error for BadRequestException
     * @param e
     * @return
     * */
    @ExceptionHandler({BadRequestException.class, ConstraintViolationException.class, MissingRequestHeaderException.class})
    public ResponseEntity<Object> batRequestException(Exception e) {
        log.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO<>(null, EResponseType.BAD_REQUEST, e.getMessage(), "400"));
    }

    /**
     * Method obtain error for INTERNAL_SERVER_ERROR
     * @param e
     * @return
     * */
    @ExceptionHandler({Exception.class, InternalErrorException.class, RuntimeException.class, IOException.class, ParseException.class})
    public final ResponseEntity<Object> exceptionsGeneral(Exception e) {
        log.info(e.getClass().getName());
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO<>(null, EResponseType.ERROR, "Se ha presentado un error inesperado", "500"));
    }
}
