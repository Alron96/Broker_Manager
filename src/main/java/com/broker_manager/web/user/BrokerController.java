package com.broker_manager.web.user;

import com.broker_manager.model.User;
import com.broker_manager.service.BrokerService;
import com.broker_manager.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BrokerController.REST_URL)
public class BrokerController {
    static final String REST_URL = "/broker";

    private final BrokerService brokerService;

    @Autowired
    public BrokerController(BrokerService brokerService) {
        this.brokerService = brokerService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return brokerService.getUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User updateUser(@PathVariable Integer id, @RequestBody UserTo userTo) {
        return brokerService.updateUser(userTo);
    }
}
