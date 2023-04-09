package com.manu.BergfexScraper.errorhandling;

public class InvalidApiKeyException extends Exception{
    public InvalidApiKeyException() {
        super();
    }

    public InvalidApiKeyException(String message) {
        super(message);
    }
}
