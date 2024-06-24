package com.app.pulse_music_sb.Repository;

import com.app.pulse_music_sb.Models.MusicTypeUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicTypeUserRepository extends JpaRepository<MusicTypeUser, String> {
}