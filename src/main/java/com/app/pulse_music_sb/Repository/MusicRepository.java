package com.app.pulse_music_sb.Repository;

import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Models.MusicType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MusicRepository extends JpaRepository<Music, String> {
    List<Music> findByMusicType(MusicType type);
    @Query(value = "SELECT * FROM Music ORDER BY play_count DESC LIMIT 1", nativeQuery = true)
    Optional<Music> findTopLikedMusic();
}