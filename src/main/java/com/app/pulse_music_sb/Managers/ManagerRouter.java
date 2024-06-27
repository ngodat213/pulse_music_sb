package com.app.pulse_music_sb.Managers;

public class ManagerRouter {
    public static final String[] UserMatchers = {
            "/", "/browse", "/artist", "/chart"
    };

    public static final String[] ArtistsMatchers = {
            "/", "/browse", "/artist", "/chart"
    };

    public static final String[] AdminMatchers = {
            "/dashboard/**"
    };

    public static final String rememberMeKey = "Freal";
    public static final int rememberMeTimeExpired = 24 * 60 * 60;

    public static final String loginPage = "/login";
    public static final String logoutPage = "/logout";
    public static final String defaultPage = "/";
}
