package com.gapatmej.predictor.domain.enumeration;

import com.gapatmej.predictor.exception.ErrorConstants;
import com.gapatmej.predictor.exception.PredictorException;

import java.util.HashMap;
import java.util.Map;

public enum Day {
    MONDAY("Monday"), TUESDAY("Tuesday"), WEDNESDAY("Wednesday"), THURSDAY("Thrusday"),
    FRIDAY("Friday"), SATURDAY("Saturday"), SUNDAY("Sunday");

    private String text;

    private String value;

    private static final Map<String, Day> lookup = new HashMap<>();

    Day(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    //****** Reverse Lookup Implementation************//
    static {
        for (Day day : Day.values()) {
            lookup.put(day.value(), day);
        }
    }

    public static Day get(String day) throws PredictorException {
        Day dayFinded = lookup.get(day);
        if(dayFinded == null)
            throw new PredictorException(ErrorConstants.getMessage(ErrorConstants.ERR_ENUM,Day.class.toString()));
        return dayFinded;

    }
}
