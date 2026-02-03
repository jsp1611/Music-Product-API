package com.sample.music.controller;

import com.sample.music.dto.ErrorDetailResponse;
import com.sample.music.exception.MissingProductException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handles exceptions thrown from below the Controller layer
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MissingProductException.class)
    public ResponseEntity<ErrorDetailResponse> handleMissing(final HttpServletRequest req,
                                                             final MissingProductException e) {
        log.error("Missing Product specified in request", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDetailResponse(req.getRequestURL().toString(), e.getMessage()));
    }

}
