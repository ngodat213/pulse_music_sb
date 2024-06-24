package com.app.pulse_music_sb.Repository;

import com.app.pulse_music_sb.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // You can define custom query methods here if needed
    @Query("select u from User u where u.email=?1")
    User findByEmail(String email);
    @Query("select u.countFail from User u where u.email=?1")
    int countFailByEmail(String email);
    @Query("select u from User u where u.tokenResetPassword=?1")
    User findByToken(String token);
    boolean existsByEmail(String email);
}
