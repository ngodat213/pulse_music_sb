package com.app.pulse_music_sb.Repository;

import com.app.pulse_music_sb.Models.CloudStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageStorageRepository extends JpaRepository<CloudStorage, String> {
}