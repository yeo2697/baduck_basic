package com.example.baduck.service.user;

import com.example.baduck.dto.response.user.UserDetailResponse;
import com.example.baduck.dto.security.UserDto;
import com.example.baduck.entity.User;
import java.util.*;

public interface UserService {
    User signUp(UserDto userDto);
    UserDetailResponse getUserDetails(String userEmail);
}
