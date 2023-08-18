package com.broker_manager.service;

import com.broker_manager.exception.UserNotFoundException;
import com.broker_manager.model.User;
import com.broker_manager.repository.UserRepository;
import com.broker_manager.to.UserTo;
import com.broker_manager.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrokerService {

    private final UserRepository userRepository;

    @Autowired
    public BrokerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserTo getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return UserUtil.asTo(user);
    }

    public List<UserTo> getAllUsers() {
        return List.of(new UserTo());
    }

    @Transactional
    public UserTo updateUser(UserTo userTo) {
        User userFromDb = userRepository.findById(userTo.getId()).orElseThrow(() -> new UserNotFoundException(userTo.getId()));
        User user = UserUtil.updateFromTo(userFromDb, userTo);
        User updatedUser = userRepository.save(user);
        return UserUtil.asTo(updatedUser);
    }
}