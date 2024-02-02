package com.example.shopapp.rest;

import com.example.shopapp.dto.request.UserLoginRequest;
import com.example.shopapp.dto.request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest){
        return null;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginDTO){
        return null;
    }


}
