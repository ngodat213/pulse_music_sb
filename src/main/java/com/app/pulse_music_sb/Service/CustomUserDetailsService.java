package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Enums.LoginType;
import com.app.pulse_music_sb.Enums.UserRole;
import com.app.pulse_music_sb.Models.CustomUserDetails;
import com.app.pulse_music_sb.Models.User;
import com.app.pulse_music_sb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService extends DefaultOAuth2UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new CustomUserDetails(user);
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        LoginType loginType = getLoginType(registrationId);

        String email = oAuth2User.getAttribute("email");
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = saveNewUser(oAuth2User, loginType);
        }

        return new CustomUserDetails(user, oAuth2User);
    }

    private LoginType getLoginType(String registrationId) {
        if ("google".equalsIgnoreCase(registrationId)) {
            return LoginType.GOOGLE;
        } else if ("facebook".equalsIgnoreCase(registrationId)) {
            return LoginType.FACEBOOK;
        } else {
            throw new IllegalArgumentException("Unsupported login type: " + registrationId);
        }
    }

    private User saveNewUser(OAuth2User oAuth2User, LoginType loginType) {
        User user = new User();
        user.setEmail(oAuth2User.getAttribute("email"));
        user.setFullName(oAuth2User.getAttribute("name"));
        user.setLoginType(loginType);
        user.setRole(UserRole.USER);
        return userRepository.save(user);
    }
}