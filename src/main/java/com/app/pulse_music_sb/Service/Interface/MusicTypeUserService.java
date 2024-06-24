package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.MusicTypeUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MusicTypeUserService {
    Page<MusicTypeUser> findAll(Pageable pageable);
    Optional<MusicTypeUser> findById(String id);
    MusicTypeUser save(MusicTypeUser musicTypeUser);
    MusicTypeUser update(MusicTypeUser musicTypeUser);
    void deleteById(String id);
}
