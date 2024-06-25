package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.MusicType;
import com.app.pulse_music_sb.Request.PaginationDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MusicTypeService {
    Page<MusicType> findAll(PaginationDTO paginationDTO);
    Optional<MusicType> findById(String id);
    MusicType save(MusicType musicType);
    MusicType update(MusicType musicType);
    void deleteById(String id);
}