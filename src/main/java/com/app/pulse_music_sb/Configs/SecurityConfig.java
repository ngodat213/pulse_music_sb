package com.app.pulse_music_sb.Configs;

import com.app.pulse_music_sb.Enums.UserRole;
import com.app.pulse_music_sb.Managers.ManagerRouter;
import com.app.pulse_music_sb.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailService;
    @Bean
    public SecurityFilterChain configure(HttpSecurity https) throws Exception {
        return https
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->request
                        .requestMatchers(ManagerRouter.UserMatchers).hasAnyAuthority(
                                UserRole.USER.getAuthority(),
                                UserRole.ARTIST.getAuthority(),
                                UserRole.ADMIN.getAuthority()
                        )
                        .requestMatchers(ManagerRouter.ArtistsMatchers).hasAnyAuthority(
                                UserRole.ARTIST.getAuthority(),
                                UserRole.ADMIN.getAuthority())
                        .requestMatchers(ManagerRouter.AdminMatchers).hasAnyAuthority(
                                UserRole.ADMIN.getAuthority()
                        )
                        .anyRequest().permitAll()
                ).formLogin(AbstractConfiguredSecurityBuilder
                        ->AbstractConfiguredSecurityBuilder
                        .loginPage(ManagerRouter.loginPage)
                        .defaultSuccessUrl(ManagerRouter.defaultPage)
                        .successHandler(new HandleSuccessLogin())
                        .permitAll()
                ).oauth2Login(oauth2Login
                        ->oauth2Login
                        .loginPage(ManagerRouter.loginPage)
                        .userInfoEndpoint(userInfoEndpoint ->
                                userInfoEndpoint.userService(customUserDetailService)
                        )
                        .successHandler(new HandleSuccessLogin())
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