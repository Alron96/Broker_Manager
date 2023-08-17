package com.broker_manager.web.user;

import com.broker_manager.service.BrokerService;
import com.broker_manager.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/broker")
public class BrokerController {

    private final BrokerService brokerService;

    @Autowired
    public BrokerController(BrokerService brokerService) {
        this.brokerService = brokerService;
    }

    @GetMapping("/users")
    @ResponseBody
    public List<UserTo> getAllUsers() {
        return brokerService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public UserTo getUserById(@PathVariable Integer id) {
        return brokerService.getUserById(id);
    }

}
