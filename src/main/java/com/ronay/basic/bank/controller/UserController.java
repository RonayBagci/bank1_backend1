package com.ronay.basic.bank.controller;

import com.ronay.basic.bank.entity.User;
import com.ronay.basic.bank.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/allUsers")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user = Optional.ofNullable(userService.findUserByUserId(id));
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("username/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName){
        Optional<User> user = Optional.ofNullable(userService.findUserByUserName(userName));
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        Optional<User> user = Optional.ofNullable(userService.findUserByEmail(email));
        return user.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping("phoneNumber/{phoneNumber}")
    public ResponseEntity<User> getUserByPhoneNumber(@PathVariable String phoneNumber){
        Optional<User> user = Optional.ofNullable(userService.findUserByPhoneNumber(phoneNumber));
        return user.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userService.checkUserExist(user)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists!");
        }
        try {
            user.setCreatedDate(LocalDateTime.now());
            User savedUser = userService.save(user); // UserService'teki save metodu kullanılıyor
            return ResponseEntity.ok(savedUser);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Email or phone number already in use!");
        }
    }


    //TODO: add Update user controller
}
