package com.gapatmej.predictor.service.dto;

import java.io.Serializable;

public class ScheduleDTO implements Serializable {

  private Long id;

  private int fromHour;

  private int fromMinute;

  private int toHour;

  private int toMinute;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getFromHour() {
    return fromHour;
  }

  public void setFromHour(int fromHour) {
    this.fromHour = fromHour;
  }

  public int getFromMinute() {
    return fromMinute;
  }

  public void setFromMinute(int fromMinute) {
    this.fromMinute = fromMinute;
  }

  public int getToHour() {
    return toHour;
  }

  public void setToHour(int toHour) {
    this.toHour = toHour;
  }

  public int getToMinute() {
    return toMinute;
  }

  public void setToMinute(int toMinute) {
    this.toMinute = toMinute;
  }
}
