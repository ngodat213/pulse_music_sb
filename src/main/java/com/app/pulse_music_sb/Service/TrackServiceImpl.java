package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.Track;
import com.app.pulse_music_sb.Repository.TrackRepository;
import com.app.pulse_music_sb.Service.Interface.TrackService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {
    @Autowired
    private TrackRepository trackRepository;

    @Override
    public Page<Track> findAll(Pageable pageable) {
        return trackRepository.findAll(pageable);
    }

    @Override
    public Optional<Track> findById(String id) {
        return trackRepository.findById(id);
    }

    @Override
    public Track save(Track track) {
        return trackRepository.save(track);
    }

    @Override
    public Track update(Track track) {
        return trackRepository.save(track);
    }

    @Override
    public void deleteById(String id) {
        trackRepository.deleteById(id);
    }
}