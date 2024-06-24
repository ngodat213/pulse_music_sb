package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.TrackType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
public interface TrackTypeService {
    Page<TrackType> findAll(Pageable pageable);
    Optional<TrackType> findById(String id);
    TrackType save(TrackType trackType);
    TrackType update(TrackType trackType);
    void deleteById(String id);
}