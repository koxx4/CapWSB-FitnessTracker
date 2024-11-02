package com.capgemini.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface for providing user-related data retrieval operations.
 */
public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with the given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId the ID of the user to be searched
     * @return an {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> findUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with the given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email the email of the user to be searched
     * @return an {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> findUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return a list of all {@link User} entities
     */
    List<User> findAllUsers();

    /**
     * Retrieves users whose email contains the specified substring.
     *
     * @param emailPart the substring of the email to search for
     * @return a list of {@link User} entities whose emails contain the specified substring
     */
    List<User> findUsersByEmailPart(String emailPart);

    /**
     * Retrieves users born before the specified date.
     *
     * @param date the date to compare with users' birthdates
     * @return a list of {@link User} entities born before the specified date
     */
    List<User> findUsersBornBefore(LocalDate date);
}
