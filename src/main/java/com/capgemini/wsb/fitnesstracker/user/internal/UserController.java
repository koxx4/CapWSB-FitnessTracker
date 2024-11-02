package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    // /v1/users/complex
    @GetMapping("complex")
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    // /v1/users/simple
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    // /v1/users/simple
    @GetMapping("/details")
    public List<UserDetailsDto> getAllDetailedUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDetailsDto)
                .toList();
    }

    @PostMapping
    public User addUser(@RequestBody UserDto userDto) {
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        return userService.createUser(userMapper.toEntity(userDto));
    }
    // DELETE /v1/users/{userId}
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }
    // /v1/users/search
    @GetMapping("/search")
    public List<UserIdEmailDto> searchUsersByEmail(@RequestParam String emailPart) {
        return userService.findUsersByEmailPart(emailPart);
    }
    // /v1/users/age
    @GetMapping("/age")
    public List<UserIdEmailDto> getUsersOlderThan(@RequestParam int age) {
        return userService.findUsersOlderThan(age);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserUpdateDto userUpdateDto) {
        return userService.updateUser(userId, userUpdateDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}