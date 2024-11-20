package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    @Override
    public List<Training> getCompletedTrainingsAfter(LocalDate date) {
        return trainingRepository.findByEndTimeAfter(date.atStartOfDay());
    }

    @Override
    public List<Training> getTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    @Override
    public Training createTraining(TrainingUpdateDto training) {
        User user = userRepository.findById(training.getUserId())
                .orElseThrow(() -> new UserNotFoundException(training.getUserId()));

        Training newTraining = new Training(
                user,
                training.getStartTime(),
                training.getEndTime(),
                ActivityType.valueOf(training.getActivityType()),
                training.getDistance(),
                training.getAverageSpeed()
        );
        return trainingRepository.save(newTraining);
    }

    @Override
    public Training updateTraining(Long id, TrainingUpdateDto training) {
        return trainingRepository.findById(id)
                .map(existingTraining -> {

                    User user = userRepository.findById(training.getUserId())
                            .orElseThrow(() -> new UserNotFoundException(training.getUserId()));

                    existingTraining.setUser(user);
                    existingTraining.setStartTime(training.getStartTime());
                    existingTraining.setEndTime(training.getEndTime());
                    existingTraining.setActivityType(ActivityType.valueOf(training.getActivityType()));
                    existingTraining.setDistance(training.getDistance());
                    existingTraining.setAverageSpeed(training.getAverageSpeed());
                    return trainingRepository.save(existingTraining);
                })
                .orElseThrow(() -> new TrainingNotFoundException(id));
    }
}
