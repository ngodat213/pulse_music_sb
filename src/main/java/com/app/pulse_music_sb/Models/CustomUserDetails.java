package com.app.pulse_music_sb.Models;

import com.app.pulse_music_sb.Const.Constants;
import com.app.pulse_music_sb.Enums.LoginType;
import jakarta.persistence.EntityListeners;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CustomUserDetails implements UserDetails, OAuth2User {
    private User user;
    private OAuth2User oAuth2User;
    private boolean isOAuth2User;

    public CustomUserDetails(User user) {
        this.user = user;
        this.isOAuth2User = false;
    }

    public CustomUserDetails(User user, OAuth2User oAuth2User) {
        this.user = user;
        this.oAuth2User = oAuth2User;
        this.isOAuth2User = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(user.getRole());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (isOAuth2User) {
            return oAuth2User.getAttributes();
        }
        return Collections.emptyMap();
    }

    @Override
    public String getName() {
        if (isOAuth2User) {
            return oAuth2User.getAttribute("name");
        }
        return user.getFullName();
    }

    public String getAvatar() {
        if (user.getAvatar() != null) {
            return user.getAvatar().getUrl();
        }
        if (isOAuth2User) {
            if (user.getLoginType() == LoginType.GOOGLE) {
                return oAuth2User.getAttribute("picture");
            }
            if (user.getLoginType() == LoginType.FACEBOOK) {
                Map<String, Object> pictureObj = oAuth2User.getAttribute("picture");
                if (pictureObj != null) {
                    Map<String, Object> data = (Map<String, Object>) pictureObj.get("data");
                    if (data != null) {
                        return (String) data.get("url");
                    }
                }
            }
        }
        return Constants.DEFAULT_AVATAR;
    }

    public String getId() {
        return user.getId();
    }

    public String getEmail() {
        if (isOAuth2User) {
            return oAuth2User.getAttribute("email");
        }
        return user.getEmail();
    }

    public String getFullName() {
        if (isOAuth2User) {
            return oAuth2User.getAttribute("fullName");
        }
        return user.getFullName();
    }

    // UserDetails specific methods
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
