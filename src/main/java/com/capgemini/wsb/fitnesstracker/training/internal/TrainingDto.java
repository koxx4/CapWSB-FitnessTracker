package com.capgemini.wsb.fitnesstracker.training.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
@AllArgsConstructor
public class TrainingDto {
    private final Long id;
    private final TrainingUserDto user;
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime startTime;
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime endTime;
    private final double distance;
    private final double averageSpeed;
    private final ActivityType activityType;
}