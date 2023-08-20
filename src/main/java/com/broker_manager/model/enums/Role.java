package com.broker_manager.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    BROKER,
    CHEIFBROKER,
    DIRECTOR;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
