package com.broker_manager.web.user;

import com.broker_manager.model.User;
import com.broker_manager.service.user.BrokerUserService;
import com.broker_manager.to.UserTo;
import com.broker_manager.web.AuthorizedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BrokerUserController.REST_URL)
public class BrokerUserController {
    static final String REST_URL = "/broker/profile";

    private final BrokerUserService brokerUserService;

    @Autowired
    public BrokerUserController(BrokerUserService brokerUserService) {
        this.brokerUserService = brokerUserService;
    }

    @GetMapping
    public User getUser(@AuthenticationPrincipal AuthorizedUser authUser) {
        return authUser.getUser();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User updateUser(@Valid @RequestBody UserTo userTo, @AuthenticationPrincipal AuthorizedUser authUser) {
        return brokerUserService.updateUser(userTo, authUser.getId());
    }
}
