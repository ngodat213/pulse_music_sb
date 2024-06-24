package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.MusicTypeUser;
import com.app.pulse_music_sb.Repository.MusicTypeUserRepository;
import com.app.pulse_music_sb.Service.Interface.MusicTypeUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MusicTypeUserServiceImpl implements MusicTypeUserService {
    @Autowired
    private MusicTypeUserRepository musicTypeUserRepository;

    @Override
    public Page<MusicTypeUser> findAll(Pageable pageable) {
        return musicTypeUserRepository.findAll(pageable);
    }

    @Override
    public Optional<MusicTypeUser> findById(String id) {
        return musicTypeUserRepository.findById(id);
    }

    @Override
    public MusicTypeUser save(MusicTypeUser musicTypeUser) {
        return musicTypeUserRepository.save(musicTypeUser);
    }

    @Override
    public MusicTypeUser update(MusicTypeUser musicTypeUser) {
        return musicTypeUserRepository.save(musicTypeUser);
    }

    @Override
    public void deleteById(String id) {
        musicTypeUserRepository.deleteById(id);
    }
}
