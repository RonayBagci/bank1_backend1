package com.ronay.basic.bank.repository;

import com.ronay.basic.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUserName(String userName);

   Optional<User> findByUserId(Long id);

   Optional<User> findUserByEmail(String email);

   Optional<User> findUserByPhoneNumber(String phoneNumber);

}
