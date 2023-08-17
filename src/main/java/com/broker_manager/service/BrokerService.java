package com.broker_manager.service;

import com.broker_manager.exception.NotFoundException;
import com.broker_manager.repository.UserToRepository;
import com.broker_manager.to.UserTo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrokerService {

    private final UserToRepository userToRepository;

    public BrokerService(UserToRepository userToRepository) {
        this.userToRepository = userToRepository;
    }

    public UserTo getUserById(Integer id) {
        return userToRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public List<UserTo> getAllUsers() {
        return userToRepository.findAll();
    }

    public UserTo saveUserTo(UserTo userTo) {
        return userToRepository.save(userTo);
    }

}