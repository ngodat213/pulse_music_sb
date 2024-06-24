package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.MusicStorage;
import com.app.pulse_music_sb.Repository.MusicStorageRepository;
import com.app.pulse_music_sb.Service.Interface.MusicStorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MusicStorageServiceImpl implements MusicStorageService {
    @Autowired
    private MusicStorageRepository musicStorageRepository;

    @Override
    public Page<MusicStorage> findAll(Pageable pageable) {
        return musicStorageRepository.findAll(pageable);
    }

    @Override
    public Optional<MusicStorage> findById(String id) {
        return musicStorageRepository.findById(id);
    }

    @Override
    public MusicStorage save(MusicStorage musicStorage) {
        return musicStorageRepository.save(musicStorage);
    }

    @Override
    public MusicStorage update(MusicStorage musicStorage) {
        return musicStorageRepository.save(musicStorage);
    }

    @Override
    public void deleteById(String id) {
        musicStorageRepository.deleteById(id);
    }
}