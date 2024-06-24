package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.Track;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TrackService {
    Page<Track> findAll(Pageable pageable);
    Optional<Track> findById(String id);
    Track save(Track track);
    Track update(Track track);
    void deleteById(String id);
}