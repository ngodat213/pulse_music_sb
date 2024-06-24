package com.app.pulse_music_sb.Repository;

import com.app.pulse_music_sb.Models.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, String> {
}