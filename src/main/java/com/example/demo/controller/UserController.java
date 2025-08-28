//package com.example.demo.controller;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.dto.AuthRequest;
//import com.example.demo.dto.UserDTO;
//import com.example.demo.service.JwtService;
//import com.example.demo.service.UserInfoService;
//
//@RestController
//@RequestMapping("/api/auth")
//public class UserController {
//	
//	private UserInfoService service;
//
//    private JwtService jwtService;
//
//    private AuthenticationManager authenticationManager;
//
//
//    @PostMapping("/register")
//    public String addNewUser(@RequestBody UserDTO userInfo) {
//        return service.addUser(userInfo);
//    }
//
//    // Removed the role checks here as they are already managed in SecurityConfig
//
//    @PostMapping("/login")
//    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//        );
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(authRequest.getUsername());
//        } else {
//            throw new UsernameNotFoundException("Invalid user request!");
//        }
//    }
//
//}
