package com.gapatmej.predictor.exception;

public class PredictorException extends  Exception {

    private String keyMessage = "";

    public PredictorException(String message) {
        super(message);
    }

    public PredictorException(String message, String keyMessage) {
        super(message);
        this.keyMessage = keyMessage;
    }

    public PredictorException(String message, String keyMessage, Throwable cause) {
        super(message, cause);
        this.keyMessage = keyMessage;
    }

    public String getKeyMessage() {
        return keyMessage;
    }
}
