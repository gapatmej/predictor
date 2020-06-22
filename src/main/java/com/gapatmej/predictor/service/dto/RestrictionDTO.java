package com.gapatmej.predictor.service.dto;

import com.gapatmej.predictor.domain.enumeration.Day;

import java.io.Serializable;

public class RestrictionDTO implements Serializable {

  private Long id;

  private int plateNumber;

  private Day day;

  private ScheduleDTO schedule;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getPlateNumber() {
    return plateNumber;
  }

  public void setPlateNumber(int plateNumber) {
    this.plateNumber = plateNumber;
  }

  public Day getDay() {
    return day;
  }

  public void setDay(Day day) {
    this.day = day;
  }

  public ScheduleDTO getSchedule() {
    return schedule;
  }

  public void setSchedule(ScheduleDTO schedule) {
    this.schedule = schedule;
  }
}
