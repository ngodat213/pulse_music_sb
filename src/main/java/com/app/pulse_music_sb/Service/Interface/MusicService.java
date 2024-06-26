package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Request.RequestCreateMusic;
import com.app.pulse_music_sb.Request.PaginationDTO;
import com.app.pulse_music_sb.Request.RequestUpdateMusic;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface MusicService {
    Page<Music> findAll(PaginationDTO paginationDTO);
    Music findById(String id);
    Music save(RequestCreateMusic createMusic, MultipartFile image, MultipartFile mp3);
    Music update(String id, RequestUpdateMusic request, MultipartFile image, MultipartFile mp3);
    void deleteById(String id);
}
