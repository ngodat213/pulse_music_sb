package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Request.Request.RequestRegisterUser;
import com.app.pulse_music_sb.Request.Request.RequestPasswordChange;
import com.app.pulse_music_sb.Request.Request.RequestPasswordReset;
import com.app.pulse_music_sb.Models.User;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<User> getAll(PaginationDTO paginationDTO);

    List<User> getArtists();

    List<Music> getUserLikedMusic(String userId);

    List<Music> getMusicPopulars(User user);

    User likeMusic(String userId, String musicId);

    User findById(String id);

    User findByEmail(String email);

    boolean existsByEmail(String email);

    User register(RequestRegisterUser req);

    User createUser(User user);

    boolean isAuthenticated();

    User currentUser();

    User getUserByUsername(String email);

    void UpdateFailCount(User user);

    boolean checkOldPassword(User authenticatedUser, String oldPassword);

    void UpdatePassword(User user, String newPassword);

    boolean handleLockUser(String id);

    void ResetLoginFail(User user);

    User getUserByEmail(String email);

    void GenTokenResetPassword(User user);

    String GenToken(int Length);

    User getUserByToken(String token);

    void ResetDateForgotPassword(User user);
}
