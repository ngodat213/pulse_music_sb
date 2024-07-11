package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.Album;
import com.app.pulse_music_sb.Models.AvailablePlan;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import com.app.pulse_music_sb.Request.Request.RequestUpdateAlbum;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IAvailablePlanService {
    Page<AvailablePlan> findAllBy(PaginationDTO paginationDTO);
    List<AvailablePlan> findAll();
    Optional<AvailablePlan> findById(String id);
    AvailablePlan save(AvailablePlan availablePlan);
    AvailablePlan update(String id, AvailablePlan availablePlan);
    void deleteById(String id);
}
