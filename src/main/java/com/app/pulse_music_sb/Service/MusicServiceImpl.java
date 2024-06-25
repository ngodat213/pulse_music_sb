package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.CloudStorage;
import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Models.User;
import com.app.pulse_music_sb.Repository.MusicRepository;
import com.app.pulse_music_sb.Request.RequestCreateMusic;
import com.app.pulse_music_sb.Service.Interface.MusicService;
import com.app.pulse_music_sb.Request.PaginationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private PaginationService paginationService;
    @Autowired
    private CloudService cloudService;
    @Autowired
    private UserService userService;

    @Override
    public Page<Music> findAll(PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        return musicRepository.findAll(pageable);
    }

    @Override
    public Optional<Music> findById(String id) {
        return musicRepository.findById(id);
    }

    @Override
    public Music save(RequestCreateMusic createMusic, MultipartFile image, MultipartFile mp3) {
        User user = userService.findById(createMusic.getUserId());

        CloudStorage imageMusic = cloudService.uploadFile(image, false);
        CloudStorage mp3Music = cloudService.uploadFile(mp3, true);

        Music music = new Music();
        music.setTitle(createMusic.getTitle());
        music.setDescription(createMusic.getDescription());
        music.setImage(imageMusic);
        music.setMp3(mp3Music);
        music.setUser(user);
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