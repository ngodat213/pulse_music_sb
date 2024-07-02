package com.app.pulse_music_sb.Enums;

public enum LoginType {
    DEFAULT("DEFAULT"),
    GOOGLE("GOOGLE"),
    FACEBOOK("FACEBOOK");

    private String loginType;
    LoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }
}
