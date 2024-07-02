package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.Album;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import com.app.pulse_music_sb.Request.Request.RequestCreateAlbum;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAlbumService {
    Page<Album> findAllBy(PaginationDTO paginationDTO);
    List<Album> findAll();
    Album findById(String id);
    Album save(RequestCreateAlbum request);
    Album update(String id, RequestCreateAlbum request);
    void deleteById(String id);
}
