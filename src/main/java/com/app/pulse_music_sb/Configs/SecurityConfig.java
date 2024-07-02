package com.app.pulse_music_sb.Configs;

import com.app.pulse_music_sb.Service.CustomOAuth2UserService;
import com.app.pulse_music_sb.Service.CustomUserDetailService;
import com.app.pulse_music_sb.Enums.UserRole;
import com.app.pulse_music_sb.Managers.ManagerRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->request
                        .requestMatchers(ManagerRouter.UserMatchers).hasAnyAuthority(
                                UserRole.USER.getAuthority(),
                                UserRole.ARTIST.getAuthority(),
                                UserRole.ADMIN.getAuthority()
                        )
                        .requestMatchers(ManagerRouter.AdminMatchers).hasAnyAuthority(
                                UserRole.ARTIST.getAuthority(),
                                UserRole.ADMIN.getAuthority())
                        .anyRequest().permitAll()
                ).formLogin(AbstractConfiguredSecurityBuilder
                        ->AbstractConfiguredSecurityBuilder
                        .loginPage(ManagerRouter.loginPage)
                        .defaultSuccessUrl(ManagerRouter.defaultPage)
                        .successHandler(new HandleSuccessLogin())
                        .permitAll()
                ).oauth2Login(oauth2Login
                        ->oauth2Login
                        .loginPage("/login")
                        .userInfoEndpoint(userInfoEndpoint ->
                                userInfoEndpoint.userService(customOAuth2UserService)
                        )
                ).rememberMe(rememberMe -> rememberMe.key(ManagerRouter.rememberMeKey)
                        .rememberMeCookieName(ManagerRouter.rememberMeKey)
                        .tokenValiditySeconds(ManagerRouter.rememberMeTimeExpired)
                        .userDetailsService(customUserDetailService)

                ).sessionManagement(sessionManagement -> sessionManagement
                        .maximumSessions(1)
                        .expiredUrl(ManagerRouter.loginPage)
                ).logout(logout->logout.logoutUrl(ManagerRouter.logoutPage)).build();
    }

    public UserDetailsService UserDetailsService() {
        return customUserDetailService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(UserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}