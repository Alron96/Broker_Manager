package com.broker_manager.service;

import com.broker_manager.model.User;
import com.broker_manager.repository.UserRepository;
import com.broker_manager.to.UserTo;
import com.broker_manager.util.UserUtil;
import com.broker_manager.util.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BrokerService {

    private final UserRepository userRepository;

    @Autowired
    public BrokerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User nof found with id=" + id));
        return user;
    }

    @Transactional
    public User updateUser(UserTo userTo) {
        User userFromDb = userRepository.findById(userTo.getId())
                .orElseThrow(() -> new NotFoundException("User nof found with id=" + userTo.getId()));
        User user = UserUtil.updateFromTo(userFromDb, userTo);
        User updatedUser = userRepository.save(user);
        return user;
    }
}