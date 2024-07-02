package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.Album;
import com.app.pulse_music_sb.Repository.AlbumRepository;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import com.app.pulse_music_sb.Request.Request.RequestCreateAlbum;
import com.app.pulse_music_sb.Service.Interface.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService implements IAlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private PaginationService paginationService;

    @Override
    public Page<Album> findAllBy(PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        return albumRepository.findAll(pageable);
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Album findById(String id) {
        return albumRepository.findById(id).get();
    }

    @Override
    public Album save(RequestCreateAlbum request) {
        return albumRepository.save(request.toEntity());
    }

    @Override
    public Album update(String id, RequestCreateAlbum request) {
        Album res = findById(id);
        res.setUser(request.getUser());
        res.setTitle(request.getTitle());
        res.setDescription(request.getDescription());
        res.setAlbumTypes(request.getAlbumTypes());
        res.setMusics(request.getMusics());
        return albumRepository.save(res);
    }

    @Override
    public void deleteById(String id) {
        Album res = findById(id);
        res.setDeleted(true);
        albumRepository.save(res);
    }
}
