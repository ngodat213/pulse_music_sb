package com.app.pulse_music_sb.Controller;

import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Service.Interface.MusicService;
import com.app.pulse_music_sb.Util.Model.PaginationDTO;
import com.app.pulse_music_sb.Util.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/music")
public class MusicController {
    @Autowired
    private MusicService musicService;

    @Autowired
    private PaginationService paginationService;

    @GetMapping
    public Page<Music> findAll(@RequestBody PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        return musicService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<Music> findById(@PathVariable String id) {
        return musicService.findById(id);
    }

    @PostMapping
    public Music save(@RequestBody Music music) {
        return musicService.save(music);
    }

    @PutMapping("/{id}")
    public Music update(@PathVariable String id, @RequestBody Music music) {
        music.setId(id);  // Set the ID to ensure update
        return musicService.update(music);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        musicService.deleteById(id);
    }
}
