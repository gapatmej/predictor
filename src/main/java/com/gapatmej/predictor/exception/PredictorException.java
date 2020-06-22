package com.gapatmej.predictor.exception;

public class PredictorException extends Exception {

  private String keyMessage = "";

  public PredictorException(String message) {
    super(message);
  }

  public String getKeyMessage() {
    return keyMessage;
  }
}
