package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MusicService {
    Page<Music> findAll(Pageable pageable);
    Optional<Music> findById(String id);
    Music save(Music music);
    Music update(Music music);
    void deleteById(String id);
}
