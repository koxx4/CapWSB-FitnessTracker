package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction,
 * whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user in the system.
     *
     * @param user the {@link User} entity to be created
     * @return the created {@link User} entity
     */
    User createUser(User user);

    /**
     * Deletes an existing user by their unique identifier.
     *
     * @param userId the unique ID of the user to be deleted
     */
    void deleteUserById(Long userId);

    /**
     * Updates an existing user's information.
     *
     * @param userId         the unique ID of the user to be updated
     * @param userUpdateDto  a data transfer object containing updated user information
     * @return an {@link Optional} containing the updated {@link User} if the user exists,
     *         or an empty {@link Optional} if the user does not exist
     */
    Optional<User> updateUser(Long userId, UserUpdateDto userUpdateDto);
}
