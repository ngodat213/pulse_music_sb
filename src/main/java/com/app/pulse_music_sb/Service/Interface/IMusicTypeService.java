package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.MusicType;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IMusicTypeService {
    Page<MusicType> findAllBy(PaginationDTO paginationDTO);
    List<MusicType> findAll();
    Optional<MusicType> findById(String id);
    MusicType save(MusicType musicType);
    MusicType update(MusicType musicType);
    void deleteById(String id);
    boolean existsById(String id);
    List<MusicType> findByIds(List<String> ids);
}