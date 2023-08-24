package com.broker_manager.service.user;

import com.broker_manager.model.User;
import com.broker_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.broker_manager.config.SecurityConfig.PASSWORD_ENCODER;

@Service
public class AbstractUserService {
    protected final UserRepository userRepository;

    @Autowired
    public AbstractUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    protected User prepareAndSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return userRepository.save(user);
    }
}
