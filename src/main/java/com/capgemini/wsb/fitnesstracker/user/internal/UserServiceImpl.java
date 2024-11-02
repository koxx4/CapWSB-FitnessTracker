package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(final Long userId) {
        log.info("Deleting User with ID {}", userId);
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> findUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUsersByEmailPart(String emailPart) {
        return userRepository.findByEmailContainingIgnoreCase(emailPart);
    }

    @Override
    public List<User> findUsersBornBefore(LocalDate date) {
        return userRepository.findByBirthdateBefore(date);
    }

    @Override
    @Transactional
    public Optional<User> updateUser(Long userId, UserUpdateDto userUpdateDto) {
        return userRepository.findById(userId)
                .map(user -> updateUser(userUpdateDto, user))
                .map(userRepository::save);
    }

    private User updateUser(UserUpdateDto userUpdateDto, User user) {
        if (userUpdateDto.firstName() != null) {
            user.setFirstName(userUpdateDto.firstName());
        }
        if (userUpdateDto.lastName() != null) {
            user.setLastName(userUpdateDto.lastName());
        }
        if (userUpdateDto.email() != null) {
            user.setEmail(userUpdateDto.email());
        }
        if (userUpdateDto.birthDate() != null) {
            user.setBirthdate(userUpdateDto.birthDate());
        }

        return user;
    }
}
