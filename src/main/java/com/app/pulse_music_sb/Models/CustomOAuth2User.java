package com.app.pulse_music_sb.Models;

import com.app.pulse_music_sb.Enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
    private OAuth2User oAuth2User;
    private User user;

    public CustomOAuth2User(OAuth2User oAuth2User, User user){
        this.oAuth2User = oAuth2User;
        this.user = user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        oAuth2User.getAuthorities().forEach(ga -> authorities.add(ga));
        authorities.add(new SimpleGrantedAuthority(UserRole.USER.getAuthority()));
        return authorities;
    }

    @Override
    public String getName() {
        return oAuth2User.getAttribute("name");
    }

    public String getEmail() {
        return oAuth2User.getAttribute("email");
    }

    public String getFullName(){
        return oAuth2User.getAttribute("fullName");
    }

    public String getAvatar() {
        return oAuth2User.getAttribute("picture");
    }

    public User getUser() {
        return user;
    }
}
