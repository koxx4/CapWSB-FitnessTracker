package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingProvider trainingProvider;
    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;

    @GetMapping
    List<TrainingDto> getAllTrainings() {
        return trainingProvider.getAllTrainings().stream()
                .map(trainingMapper::toTrainingDto)
                .toList();
    }

    @GetMapping("/user/{userId}")
    List<TrainingDto> getTrainingsByUser(@PathVariable Long userId) {
        return trainingProvider.getTrainingsByUserId(userId).stream()
                .map(trainingMapper::toTrainingDto)
                .toList();
    }

    @GetMapping("/completed/{date}")
    List<TrainingDto> getCompletedTrainingsAfter(@PathVariable LocalDate date) {
        return trainingProvider.getCompletedTrainingsAfter(date).stream()
                .map(trainingMapper::toTrainingDto)
                .toList();
    }

    @GetMapping("/activityType")
    List<TrainingDto> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingProvider.getTrainingsByActivityType(activityType).stream()
                .map(trainingMapper::toTrainingDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    TrainingDto createTraining(@RequestBody TrainingUpdateDto training) {
        return trainingMapper.toTrainingDto(trainingService.createTraining(training));
    }

    @PutMapping("/{id}")
    TrainingDto updateTraining(@PathVariable Long id, @RequestBody TrainingUpdateDto training) {
        return trainingMapper.toTrainingDto(trainingService.updateTraining(id, training));
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(TrainingNotFoundException.class)
    ResponseEntity<String> handleTrainingNotFoundException(TrainingNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
    }
}
