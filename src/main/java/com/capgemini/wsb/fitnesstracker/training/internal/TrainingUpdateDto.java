package com.capgemini.wsb.fitnesstracker.training.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TrainingUpdateDto {
    private final Long userId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String activityType;
    private final double distance;
    private final double averageSpeed;
}