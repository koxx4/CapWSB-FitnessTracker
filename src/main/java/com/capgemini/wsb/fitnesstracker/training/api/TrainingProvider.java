package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);

    List<Training> getAllTrainings();

    List<Training> getTrainingsByUserId(Long userId);

    List<Training> getCompletedTrainingsAfter(LocalDate date);

    List<Training> getTrainingsByActivityType(ActivityType activityType);
}
