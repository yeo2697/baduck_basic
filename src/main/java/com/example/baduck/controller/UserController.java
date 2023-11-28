package com.example.baduck.controller;

import com.example.baduck.dto.response.GlobalResponse;
import com.example.baduck.dto.response.user.UserDetailResponse;
import com.example.baduck.dto.security.UserDto;
import com.example.baduck.entity.User;

import com.example.baduck.handler.exception.ErrorCode;
import com.example.baduck.handler.exception.ErrorResponse;
import com.example.baduck.repository.UserRepository;
import com.example.baduck.service.user.UserService;
import com.example.baduck.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController (UserService userService, UserRepository userRepository)
    {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/sign-up")
    public ResponseEntity <Object> singUp (
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.signUp(userDto));
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_GENERAL')")
    public ResponseEntity <Object> getUserDetails () {
        String userEmail = SecurityUtil.getCurrentUserEmail().orElseThrow();

        return ResponseEntity.ok(GlobalResponse.builder()
                        .status(HttpStatus.OK.value())
                        .data(userService.getUserDetails(userEmail))
                .build());
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity <Object> getUsers (
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);

        Page<UserDetailResponse> userDetailResponsePage = userRepository.findAll(pageable)
                .map(UserDetailResponse::new);

        return ResponseEntity.ok(GlobalResponse.builder()
                        .status(HttpStatus.OK.value())
                        .data(userDetailResponsePage)
                .build());
    }
}
