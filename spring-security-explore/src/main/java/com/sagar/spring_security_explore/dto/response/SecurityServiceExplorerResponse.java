package com.sagar.spring_security_explore.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SecurityServiceExplorerResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public SecurityServiceExplorerResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

}

