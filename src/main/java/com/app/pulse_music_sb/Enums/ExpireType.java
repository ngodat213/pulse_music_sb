package com.app.pulse_music_sb.Enums;

public enum ExpireType {
    DAY("DAY"),
    MONTH("MONTH"),
    YEAR("YEAR");

    private String expireType;
    ExpireType(String expireType) {
        this.expireType = expireType;
    }

    public String getExpireTypeType() {
        return expireType;
    }
}
