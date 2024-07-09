package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.Playlist;
import com.app.pulse_music_sb.Repository.PlaylistRepository;
import com.app.pulse_music_sb.Service.Interface.IPlaylistService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaylistService implements IPlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Override
    public Page<Playlist> findAll(Pageable pageable) {
        return playlistRepository.findAll(pageable);
    }

    @Override
    public Optional<Playlist> findById(String id) {
        return playlistRepository.findById(id);
    }

    @Override
    public Playlist save(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public Playlist update(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public void deleteById(String id) {
        playlistRepository.deleteById(id);
    }
}
