package com.broker_manager.repository;

import com.broker_manager.to.UserTo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserToRepository extends JpaRepository<UserTo, Integer> {
}
