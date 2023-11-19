package com.example.baduck.controller;

import com.example.baduck.dto.security.UserDto;
import com.example.baduck.entity.User;
import com.example.baduck.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity <User> singUp (
            @Valid @RequestBody UserDto userDto
    ) {
        try {
            return ResponseEntity.ok(userService.signUp(userDto));
        } catch (Exception e) {
            System.out.println("Error => " + e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
