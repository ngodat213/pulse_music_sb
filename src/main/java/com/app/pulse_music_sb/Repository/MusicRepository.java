package com.app.pulse_music_sb.Repository;

import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Models.MusicType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MusicRepository extends JpaRepository<Music, String> {
    List<Music> findAllByMusicType(MusicType type);
    @Query(value = "SELECT * FROM Music ORDER BY play_count DESC LIMIT 1", nativeQuery = true)
    Optional<Music> findTopLikedMusic();

    @Query("SELECT p FROM Music p WHERE " +
            "p.title LIKE CONCAT('%',:query, '%')" +
            "Or p.description LIKE CONCAT('%', :query, '%')")
    List<Music> searchMusics(String query);

    @Query("SELECT m FROM Music m WHERE m.createdAt >= :startTime ORDER BY m.playCount DESC, m.heartCount DESC")
    List<Music> findByCreatedAtAfter(@Param("startTime") LocalDateTime startTime);
}