package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Repository.MusicRepository;
import com.app.pulse_music_sb.Service.Interface.MusicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicRepository musicRepository;

    @Override
    public Page<Music> findAll(Pageable pageable) {
        return musicRepository.findAll(pageable);
    }

    @Override
    public Optional<Music> findById(String id) {
        return musicRepository.findById(id);
    }

    @Override
    public Music save(Music music) {
        return musicRepository.save(music);
    }

    @Override
    public Music update(Music music) {
        return musicRepository.save(music);
    }

    @Override
    public void deleteById(String id) {
        musicRepository.deleteById(id);
    }
}