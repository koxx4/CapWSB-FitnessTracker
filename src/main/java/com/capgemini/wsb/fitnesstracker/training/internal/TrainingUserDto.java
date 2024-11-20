package com.capgemini.wsb.fitnesstracker.training.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class TrainingUserDto {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
}
