package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Repository interface for {@link User} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and custom queries.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Searches for a user by their exact email address.
     *
     * @param email the email of the user to search for
     * @return an {@link Optional} containing the found user, or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Retrieves a list of users whose email contains the specified substring, ignoring case.
     *
     * @param emailPart the substring to search for within user emails
     * @return a list of {@link User} entities whose emails contain the specified substring
     */
    List<User> findByEmailContainingIgnoreCase(String emailPart);

    /**
     * Retrieves a list of users who were born before the specified date.
     *
     * @param date the date to compare with users' birth dates
     * @return a list of {@link User} entities born before the specified date
     */
    List<User> findByBirthdateBefore(LocalDate date);
}