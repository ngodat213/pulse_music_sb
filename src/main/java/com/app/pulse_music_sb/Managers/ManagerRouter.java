package com.app.pulse_music_sb.Managers;

public class ManagerRouter {
    public static final String[] UserMatchers = {
            "/",
            "/browse",
            "/artist",
            "/chart",
            "/artist_detail/**",
            "/profile",
            "/album/**",
            "/profile_change",
    };

    public static final String[] ArtistsMatchers = {
            "/dashboard/artist_table/**",
            "/dashboard/chart_table/**",
            "/dashboard/artist_detail/**",
    };

    public static final String[] AdminMatchers = {
            "/dashboard/user_table/**",
    };

    public static final String rememberMeKey = "Pulse";
    public static final int rememberMeTimeExpired = 24 * 60 * 60;

    public static final String loginPage = "/login";
    public static final String logoutPage = "/logout";
    public static final String defaultPage = "/";
}
