package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Models.MusicType;
import com.app.pulse_music_sb.Request.Request.RequestCreateMusic;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import com.app.pulse_music_sb.Request.Request.RequestMusicTypes;
import com.app.pulse_music_sb.Request.Request.RequestUpdateMusic;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MusicService {
    Page<Music> findAllBy(PaginationDTO paginationDTO);
    List<Music> findAll();
    Music findById(String id);
    Music save(RequestCreateMusic createMusic, MultipartFile image, MultipartFile mp3);
    Music update(String id, RequestUpdateMusic request, MultipartFile image, MultipartFile mp3);
    void deleteById(String id);
    List<RequestMusicTypes> findAllMusicTypes(Page<MusicType> types);
    List<Music> findByIds(List<String> ids);
}
