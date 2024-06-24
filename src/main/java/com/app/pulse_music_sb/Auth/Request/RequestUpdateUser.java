package com.app.pulse_music_sb.Auth.Request;

import com.app.pulse_music_sb.Models.User;
import com.app.pulse_music_sb.Util.Model.ImageStorage;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateUser {
    private String password;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String bio;
    private ImageStorage avatar;

    public User toUpdate(User user){
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setEmail(email);
        user.setAvatar(avatar);
        user.setPhone(phone);
        user.setBio(bio);
        return user;
    }
}
