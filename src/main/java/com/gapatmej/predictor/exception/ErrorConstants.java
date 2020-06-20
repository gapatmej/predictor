package com.gapatmej.predictor.exception;

public final class ErrorConstants {
    public static final String ERR_ENUM = "Error to get Enum : %s";

    public static String getMessage(String error, Object... args){
        return String.format(error, args);
    }

}
