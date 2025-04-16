package com.ronay.basic.bank.service;

import com.ronay.basic.bank.entity.User;
import com.ronay.basic.bank.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    //TODO: Make interface for userService and turn this UserServiceImpl class
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName).
                orElseThrow(() -> new RuntimeException("User not found with username : " + userName));
    }

    public User findUserByUserId(Long id) {
        return userRepository.findByUserId(id).orElseThrow(() -> new RuntimeException("User not found :( "));
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() ->
                new RuntimeException("User not found with email : " + email));
    }

    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber).
                orElseThrow(() -> new RuntimeException("User not found with phone number : " + phoneNumber));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean checkEmailExist(String email) {
        return findUserByEmail(email) != null;
    }

    public boolean checkPhoneNumberExist(String phoneNumber) {
        return findUserByPhoneNumber(phoneNumber) != null;
    }

    public boolean checkUserExist(User user) {

        return userRepository.findByUserName(user.getUserName()).isPresent()
                || userRepository.findUserByEmail(user.getEmail()).isPresent()
                || userRepository.findUserByPhoneNumber(user.getPhoneNumber()).isPresent();

    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> createUser(User user) {

        if (checkUserExist(user)) {
            //User already exists
            System.out.println("This user already exist !!!"); //TODO: Use log functions
            return Optional.empty();
        } else {
            //the user doesn't exist

            user.setCreatedDate(LocalDateTime.now());

            User savedUser = userRepository.save(user);

            return Optional.of(savedUser);
        }
    }



    //TODO: add delete methods (soft deleting)
    //TODO :give log info before deleting(may be money is still in the account !!!)
    //TODO: add Update user method
}
