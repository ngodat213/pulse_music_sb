package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.MusicStorage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MusicStorageService {
    Page<MusicStorage> findAll(Pageable pageable);
    Optional<MusicStorage> findById(String id);
    MusicStorage save(MusicStorage musicStorage);
    MusicStorage update(MusicStorage musicStorage);
    void deleteById(String id);
}