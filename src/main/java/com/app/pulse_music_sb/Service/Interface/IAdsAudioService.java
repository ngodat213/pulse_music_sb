package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.AdsAudio;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import com.app.pulse_music_sb.Request.Request.RequestCreateAdsAudio;
import com.app.pulse_music_sb.Request.Request.RequestCreateAlbum;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IAdsAudioService {
    Page<AdsAudio> findAllBy(PaginationDTO paginationDTO);
    List<AdsAudio> findAll();
    Optional<AdsAudio> findById(String id);
    AdsAudio save(RequestCreateAdsAudio request, MultipartFile image);
    void deleteById(String id);
}
