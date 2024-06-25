package com.app.pulse_music_sb.Controller;

import com.app.pulse_music_sb.Models.Track;
import com.app.pulse_music_sb.Service.Interface.TrackService;
import com.app.pulse_music_sb.Request.PaginationDTO;
import com.app.pulse_music_sb.Service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {
    @Autowired
    private TrackService trackService;

    @Autowired
    private PaginationService paginationService;

    @GetMapping
    public Page<Track> findAll(@RequestBody PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        return trackService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<Track> findById(@PathVariable String id) {
        return trackService.findById(id);
    }

    @PostMapping
    public Track save(@RequestBody Track track) {
        return trackService.save(track);
    }

    @PutMapping("/{id}")
    public Track update(@PathVariable String id, @RequestBody Track track) {
        track.setId(id);  // Set the ID to ensure update
        return trackService.update(track);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        trackService.deleteById(id);
    }
}
