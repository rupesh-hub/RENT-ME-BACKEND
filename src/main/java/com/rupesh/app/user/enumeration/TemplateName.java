package com.rupesh.app.user.enumeration;

import lombok.Getter;

@Getter
public enum TemplateName {

    ACTIVATE_ACCOUNT("activate_account");
    private final String name;

    TemplateName(String name){
        this.name = name;
    }

}
