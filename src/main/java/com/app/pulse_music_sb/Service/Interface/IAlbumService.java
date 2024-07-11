package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.Album;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import com.app.pulse_music_sb.Request.Request.RequestCreateAlbum;
import com.app.pulse_music_sb.Request.Request.RequestUpdateAlbum;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IAlbumService {
    Page<Album> findAllBy(PaginationDTO paginationDTO);
    List<Album> findAll();
    Optional<Album> findById(String id);
    Album save(RequestCreateAlbum request, MultipartFile image);
    Album update(String id, RequestUpdateAlbum request);
    void deleteById(String id);
    boolean existsById(String id);
}
