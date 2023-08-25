package com.broker_manager.service.user;

import com.broker_manager.model.User;
import com.broker_manager.repository.UserRepository;
import com.broker_manager.to.UserTo;
import com.broker_manager.util.UserUtil;
import com.broker_manager.util.error.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BrokerUserService extends AbstractUserService {

    public BrokerUserService(UserRepository userRepository) {
        super(userRepository);
    }

    @Transactional
    public User updateUser(UserTo userTo, Integer id) {
        userTo.setId(id);
        User userFromDb = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User nof found"));
        User user = UserUtil.updateFromTo(userFromDb, userTo);
        return prepareAndSave(user);
    }
}