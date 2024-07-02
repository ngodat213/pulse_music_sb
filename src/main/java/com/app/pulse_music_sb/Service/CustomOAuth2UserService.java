package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Enums.LoginType;
import com.app.pulse_music_sb.Enums.UserRole;
import com.app.pulse_music_sb.Models.CustomOAuth2User;
import com.app.pulse_music_sb.Models.User;
import com.app.pulse_music_sb.Repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setFullName(oAuth2User.getAttribute("name"));
            user.setLoginType(LoginType.GOOGLE);
            user.setRole(UserRole.USER);
            userRepository.save(user);
        }

        return new CustomOAuth2User(oAuth2User, user);
    }
}
