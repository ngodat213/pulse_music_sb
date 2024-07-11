package com.app.pulse_music_sb.Enums;


import org.springframework.security.core.GrantedAuthority;

public enum UserType implements GrantedAuthority {
    NORMAL("NORMAL"),
    PREMIUM("PREMIUM");

    private String authority;
    UserType(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
