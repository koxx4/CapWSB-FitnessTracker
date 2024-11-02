package com.capgemini.wsb.fitnesstracker.user.api;

import java.time.LocalDate;

public record UserUpdateDto(String firstName, String lastName, String email, LocalDate birthDate) {
}
