package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.Album;
import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Models.MusicType;
import com.app.pulse_music_sb.Models.User;
import com.app.pulse_music_sb.Repository.AlbumRepository;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import com.app.pulse_music_sb.Request.Request.RequestCreateAlbum;
import com.app.pulse_music_sb.Request.Request.RequestUpdateAlbum;
import com.app.pulse_music_sb.Service.Interface.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService implements IAlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private PaginationService paginationService;
    @Autowired
    private UserService userService;
    @Autowired
    private MusicTypeServiceImpl musicTypeServiceImpl;
    @Autowired
    private MusicServiceImpl musicServiceImpl;

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
    public Optional<Album> findById(String id) {
        return albumRepository.findById(id);
    }

    @Override
    public Album save(RequestCreateAlbum request) {
        System.out.println("loading save");
        List<MusicType> musicTypes = musicTypeServiceImpl.findByIds(request.getAlbumTypes());
        List<Music> musics = musicServiceImpl.findByIds(request.getMusics());

        User user = userService.findById(request.getUserId());
        Album album = request.toEntity();
        album.setUser(user);
        album.setMusics(musics);
        album.setAlbumTypes(musicTypes);
        return albumRepository.save(album);
    }

    @Override
    public Album update(String id, RequestUpdateAlbum request) {
        Album res = findById(id).get();
        res.setUser(request.getUser());
        res.setTitle(request.getTitle());
        res.setDescription(request.getDescription());
        res.setAlbumTypes(request.getAlbumTypes());
        res.setMusics(request.getMusics());
        return albumRepository.save(res);
    }

    @Override
    public void deleteById(String id) {
        Album res = findById(id).get();
        res.setDeleted(true);
        albumRepository.save(res);
    }

    @Override
    public boolean existsById(String id) {
        return albumRepository.existsById(id);
    }
}
