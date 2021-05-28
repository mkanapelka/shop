package com.example.shop.service;

import com.example.shop.entity.User;
import com.example.shop.exception.UserNotFoundException;
import com.example.shop.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByName(String name){
        return userRepository.findByName(name).orElseThrow(UserNotFoundException::new);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
