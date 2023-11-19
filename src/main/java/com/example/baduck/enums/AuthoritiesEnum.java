package com.example.baduck.enums;

public enum AuthoritiesEnum {
    ADMIN("ADMIN"),
    GENERAL("GENERAL"),
    ADULT("ADULT"),
    COMPANY("COMPANY");

    private final String authorityName;

    AuthoritiesEnum(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityName () {
        return authorityName;
    }
}
