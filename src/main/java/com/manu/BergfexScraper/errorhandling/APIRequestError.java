package com.manu.BergfexScraper.errorhandling;

import org.springframework.http.HttpStatus;
import java.text.SimpleDateFormat;
import java.util.Date;

public class APIRequestError {

    private HttpStatus httpStatus;
    private String timestamp;
    private String message;

    public APIRequestError() {
    }

    public APIRequestError(String message, HttpStatus status, Throwable ex) {
        this.httpStatus = status;
        this.timestamp = formattedDate();
        this.message = message;
    }

    private String formattedDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        return formatter.format(date);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
