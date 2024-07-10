package com.app.pulse_music_sb.Repository;

import com.app.pulse_music_sb.Enums.UserRole;
import com.app.pulse_music_sb.Models.Album;
import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Models.Playlist;
import com.app.pulse_music_sb.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    List<User> findAllByRole(UserRole role);
    @Query("SELECT m FROM Music m WHERE m.user.id = :userId ORDER BY m.playCount DESC")
    List<Music> findTop4ByUserIdOrderByPlayCountDesc(String userId);
    @Query("SELECT a FROM Album a WHERE a.user.id = :userId ORDER BY a.title DESC")
    List<Album> findAllAlbumsByUserId(String userId);
    @Query("SELECT m FROM Music m WHERE m.user.id = :userId ORDER BY m.playCount DESC")
    List<Music> findAllTracksByUserId(String userId);
    @Query("SELECT m FROM Playlist m WHERE m.user.id = :userId ORDER BY m.playCount DESC")
    List<Playlist> findAllPlaylistsByUserId(String userId);
    @Query("SELECT u FROM User u WHERE " +
            "u.fullName LIKE CONCAT('%', :query, '%') AND " +
            "u.role = 'ARTIST'")
    List<User> searchArtist(String query);
}
