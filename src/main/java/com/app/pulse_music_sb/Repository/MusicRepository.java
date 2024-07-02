package com.app.pulse_music_sb.Repository;

import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Models.MusicType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, String> {
    List<Music> findByMusicType(MusicType type);
}