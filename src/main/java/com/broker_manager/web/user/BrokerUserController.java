package com.broker_manager.web.user;

import com.broker_manager.model.User;
import com.broker_manager.service.user.BrokerUserService;
import com.broker_manager.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BrokerUserController.REST_URL)
public class BrokerUserController {
    static final String REST_URL = "/broker";

    private final BrokerUserService brokerUserService;

    @Autowired
    public BrokerUserController(BrokerUserService brokerUserService) {
        this.brokerUserService = brokerUserService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return brokerUserService.getUserById(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User updateUser(@RequestBody UserTo userTo) {
        return brokerUserService.updateUser(userTo);
    }
}
