package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Repository.MusicRepository;
import com.app.pulse_music_sb.Request.Request.RequestRegisterUser;
import com.app.pulse_music_sb.Enums.UserRole;
import com.app.pulse_music_sb.Exceptions.DuplicateResourceException;
import com.app.pulse_music_sb.Exceptions.ResourceNotFoundException;
import com.app.pulse_music_sb.Models.User;
import com.app.pulse_music_sb.Repository.UserRepository;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import com.app.pulse_music_sb.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaginationService paginationService;
    @Autowired
    private MusicRepository musicRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll(PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        return userRepository.findAll(pageable).toList();
    }

    @Override
    public List<User> getArtists(){
        System.out.println(UserRole.ARTIST.getAuthority());
        return userRepository.findAllByRole(UserRole.ARTIST);
    }

    @Override
    public List<Music> getUserLikedMusic(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUserLiked();
    }

    @Override
    public List<Music> getMusicPopulars(User user) {
        return userRepository.findTop4ByUserIdOrderByPlayCountDesc(user.getId());
    }

    @Override
    public User likeMusic(String userId, String musicId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Music music = musicRepository.findById(musicId).orElseThrow(() -> new RuntimeException("Music not found"));
        user.getUserLiked().add(music);
        return userRepository.save(user);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User register(RequestRegisterUser req) {
        if(!userRepository.existsByEmail(req.getEmail()) && !req.getPassword().isEmpty()){
            if(checkPassword(req.getPassword(), req.getConfirmPassword())) {
                req.setPassword(encodePassword(req.getPassword()));

                final User user = req.toUser();
                return userRepository.saveAndFlush(user);
            }else{
                throw new DuplicateResourceException("Password and confirm password is not equals");
            }
        }else{
            throw new DuplicateResourceException("The user with email [%s] already exists".formatted(req.getEmail()));
        }
    }

    @Override
    public User createUser(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        Authentication auth = securityContext.getAuthentication();

        return auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated();
    }

    @Override
    public User currentUser() {
        if(!isAuthenticated()){
            return null;
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();

        Authentication auth = securityContext.getAuthentication();

        return userRepository.findByEmail(auth.getName());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public boolean checkOldPassword(User user, String oldPassword) {
        if(checkCryptCompare(user.getPassword(), oldPassword)) {
            return true;
        }
        return false;
    }

    @Override
    public void UpdatePassword(User user, String newPassword) {
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public boolean handleLockUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The user with id [%s] not exists"
                        .formatted(id)));
        if(user.getRole().getAuthority().equals(UserRole.ADMIN.getAuthority())){
            return false;
        }
        user.setEnabled(!user.isEnabled());
        userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public User getUserByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void UpdateFailCount(User user){
        int count = userRepository.countFailByEmail(user.getEmail());
        if(user.getLockExpired().getTime()<System.currentTimeMillis()){
            user.setEnabled(true);
            user.setLockExpired(null);
            user.setCountFail(0);
        }
        user.setCountFail(count+1);
        if(count==3){
            user.setEnabled(false);
            user.setLockExpired(new Date(System.currentTimeMillis()+1000*60*15));
        }
        userRepository.save(user);
    }

    @Override
    public void ResetLoginFail(User user){
        user.setLockExpired(null);
        user.setCountFail(0);
        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void GenTokenResetPassword(User user){
        user.setTokenResetPassword(GenToken(45));
        user.setTokenResetPasswordExpired(new Date(System.currentTimeMillis()+1000*60*10));
        userRepository.save(user);
    }

    @Override
    public String GenToken(int Length){
        return "1111";
    }

    @Override
    public User getUserByToken(String token){
        return userRepository.findByToken(token);
    }

    @Override
    public void ResetDateForgotPassword(User user){
        user.setTokenResetPasswordExpired(null);
        user.setTokenResetPassword(null);
        userRepository.save(user);
    }

    // Private method
    private boolean checkCryptCompare(String password, String oldPassword){
        return new BCryptPasswordEncoder().matches(oldPassword, password);
    }

    private boolean checkPassword(String password, String comfirmPassword) {
        return password.equals(comfirmPassword);
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}