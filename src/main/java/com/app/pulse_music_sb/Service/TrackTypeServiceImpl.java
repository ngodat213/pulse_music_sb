package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.TrackType;
import com.app.pulse_music_sb.Repository.TrackTypeRepository;
import com.app.pulse_music_sb.Service.Interface.TrackTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrackTypeServiceImpl implements TrackTypeService {
    @Autowired
    private TrackTypeRepository trackTypeRepository;

    @Override
    public Page<TrackType> findAll(Pageable pageable) {
        return trackTypeRepository.findAll(pageable);
    }

    @Override
    public Optional<TrackType> findById(String id) {
        return trackTypeRepository.findById(id);
    }

    @Override
    public TrackType save(TrackType trackType) {
        return trackTypeRepository.save(trackType);
    }

    @Override
    public TrackType update(TrackType trackType) {
        return trackTypeRepository.save(trackType);
    }

    @Override
    public void deleteById(String id) {
        trackTypeRepository.deleteById(id);
    }
}