package com.broker_manager.web.user;

import com.broker_manager.model.User;
import com.broker_manager.model.enums.Department;
import com.broker_manager.service.ChiefBrokerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chief_broker")
public class ChiefBrokerController {

    private final ChiefBrokerService chiefBrokerService;

    public ChiefBrokerController(ChiefBrokerService chiefBrokerService) {
        this.chiefBrokerService = chiefBrokerService;
    }

    @GetMapping("/{department}")
    public List<User> getBrokersByDepartment(@PathVariable Department department) {
        return chiefBrokerService.getBrokersByDepartment(department);
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return chiefBrokerService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @Valid @RequestBody User updatedUser) {
        return chiefBrokerService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        chiefBrokerService.deleteUser(id);
    }
}
