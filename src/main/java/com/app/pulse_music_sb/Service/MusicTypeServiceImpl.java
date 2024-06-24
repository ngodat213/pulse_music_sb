package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.MusicType;
import com.app.pulse_music_sb.Repository.MusicTypeRepository;
import com.app.pulse_music_sb.Service.Interface.MusicTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MusicTypeServiceImpl implements MusicTypeService {
    @Autowired
    private MusicTypeRepository musicTypeRepository;

    @Override
    public Page<MusicType> findAll(Pageable pageable) {
        return musicTypeRepository.findAll(pageable);
    }

    @Override
    public Optional<MusicType> findById(String id) {
        return musicTypeRepository.findById(id);
    }

    @Override
    public MusicType save(MusicType musicType) {
        return musicTypeRepository.save(musicType);
    }

    @Override
    public MusicType update(MusicType musicType) {
        return musicTypeRepository.save(musicType);
    }

    @Override
    public void deleteById(String id) {
        musicTypeRepository.deleteById(id);
    }
}