package com.khalid.assignment.service;

import com.khalid.assignment.dto.UserDto;
import com.khalid.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto findByUsername(String username) {
        return userRepository.findByUserName(username).map(UserDto::new).orElseThrow(UserNotFoundException::new);
    }
}
