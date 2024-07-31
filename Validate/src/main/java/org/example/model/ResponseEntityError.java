package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Getter
@Setter
public class ResponseEntityError {

    private LocalDateTime timestamp;

    private Integer status;

    private String error;

    private String message;

    private String path;

    public ResponseEntityError() {
    }

    public ResponseEntityError(HttpStatus httpStatus, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = httpStatus.value();
        this.error = httpStatus.name();
        this.message = message;
        this.path = path;
    }



}
