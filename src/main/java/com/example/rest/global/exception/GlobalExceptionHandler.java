package com.example.rest.global.exception;

import com.example.rest.global.app.AppConfig;
import com.example.rest.global.dto.RsData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<RsData<Void>> handle(NoSuchElementException e) {

        // 개발 모드에서만 작동되도록.
        if(AppConfig.isNotProd()) e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new RsData<>(
                                "404-1",
                                "해당 데이터가 존재하지 않습니다."
                        )
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RsData<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors()
                .stream()
                .map(fe -> fe.getField() + " : " + fe.getCode() + " : "  + fe.getDefaultMessage())
                .sorted()
                .collect(Collectors.joining("\n"));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new RsData<>(
                                "400-1",
                                message
                        )
                );
    }

    @ExceptionHandler(RuntimeException.class) // RunTimeException 사용하면 상속받는 다양한 예외들을 사용 가능
    public ResponseEntity<RsData<Void>> DataIntegrityViolationExceptionHandle(RuntimeException ex) {

        // 개발 모드에서만 작동되도록.
        if(AppConfig.isNotProd()) ex.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new RsData<>(
                                "409-1",
                                ex.getMessage()
                        )
                );
    }
}
