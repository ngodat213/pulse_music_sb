package com.app.pulse_music_sb.Auth.Request;

import com.app.pulse_music_sb.Enums.UserRole;
import com.app.pulse_music_sb.Models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestRegisterUser {
    @Email()
    private String email;

    @Size.List({
            @Size(min = 8, message = "Password too short"),
            @Size(max = 80, message = "Password too long")
    })
    private String confirmPassword;

    @Size.List({
            @Size(min = 8, message = "Password too short"),
            @Size(max = 80, message = "Password too long")
    })
    private String password;

    public User toUser(){
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        user.setEnabled(true);
        user.setRole(UserRole.USER);
        return user;
    }
}
