package com.app.pulse_music_sb.Repository;

import com.app.pulse_music_sb.Models.MusicType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MusicTypeRepository extends JpaRepository<MusicType, String> {
    Optional<MusicType> findByTypeName(String name);
}
