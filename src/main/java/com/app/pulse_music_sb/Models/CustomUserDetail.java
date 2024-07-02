package com.app.pulse_music_sb.Models;

import com.app.pulse_music_sb.Const.Constants;
import jakarta.persistence.EntityListeners;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CustomUserDetail implements UserDetails{
    private User user;
    public CustomUserDetail(User user) {
        this.user = user;
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

    public String getAvatar(){
        if(user.getAvatar()!=null){
            return user.getAvatar().getUrl();
        }
        return Constants.DEFAULT_AVATAR;
    }

    public String getId(){
        return user.getId();
    }
}
