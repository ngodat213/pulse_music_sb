package com.app.pulse_music_sb.Controller;

import com.app.pulse_music_sb.Models.Playlist;
import com.app.pulse_music_sb.Service.Interface.PlaylistService;
import com.app.pulse_music_sb.Request.PaginationDTO;
import com.app.pulse_music_sb.Service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private PaginationService paginationService;

    @GetMapping
    public Page<Playlist> findAll(@RequestBody PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        return playlistService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<Playlist> findById(@PathVariable String id) {
        return playlistService.findById(id);
    }

    @PostMapping
    public Playlist save(@RequestBody Playlist playlist) {
        return playlistService.save(playlist);
    }

    @PutMapping("/{id}")
    public Playlist update(@PathVariable String id, @RequestBody Playlist playlist) {
        playlist.setId(id);  // Set the ID to ensure update
        return playlistService.update(playlist);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        playlistService.deleteById(id);
    }
}
