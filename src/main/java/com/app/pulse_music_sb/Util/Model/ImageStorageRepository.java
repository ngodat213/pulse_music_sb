package com.app.pulse_music_sb.Util.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageStorageRepository extends JpaRepository<ImageStorage, String> {
}