package com.app.pulse_music_sb.Repository;

import com.app.pulse_music_sb.Models.MusicStorage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicStorageRepository extends JpaRepository<MusicStorage, String> {
}