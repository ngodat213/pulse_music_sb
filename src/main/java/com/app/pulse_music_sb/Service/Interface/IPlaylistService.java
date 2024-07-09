package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPlaylistService {
    Page<Playlist> findAll(Pageable pageable);
    Optional<Playlist> findById(String id);
    Playlist save(Playlist playlist);
    Playlist update(Playlist playlist);
    void deleteById(String id);
}
