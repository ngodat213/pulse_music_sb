package com.app.pulse_music_sb.Auth.Request;

import com.app.pulse_music_sb.Models.User;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestLoginUser {
    private String password;
    private String email;

    public User toUser(){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}