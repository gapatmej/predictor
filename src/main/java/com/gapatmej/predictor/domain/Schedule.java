package com.gapatmej.predictor.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "schedule")
public class Schedule  extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(0)
    @Max(24)
    @Column(name = "fromHour", nullable = false)
    private int fromHour;

    @NotNull
    @Min(0)
    @Max(60)
    @Column(name = "fromMinute", nullable = false)
    private int fromMinute;

    @NotNull
    @Min(0)
    @Max(24)
    @Column(name = "toHour", nullable = false)
    private int toHour;

    @NotNull
    @Min(0)
    @Max(60)
    @Column(name = "toMinute", nullable = false)
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
