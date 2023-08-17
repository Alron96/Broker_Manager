package com.broker_manager.service;

import com.broker_manager.exception.NotFoundException;
import com.broker_manager.repository.UserRepository;
import com.broker_manager.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrokerService {

    private final UserRepository userRepository;

    @Autowired
    public BrokerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserTo getUserById(Integer id) {
        return new UserTo();
    }

    public List<UserTo> getAllUsers() {
        return List.of(new UserTo());
    }

    public UserTo updateUser(UserTo userTo) {
        return new UserTo();
    }

}