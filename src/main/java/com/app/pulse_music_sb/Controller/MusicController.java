package com.app.pulse_music_sb.Controller;

import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Request.RequestCreateMusic;
import com.app.pulse_music_sb.Service.Interface.MusicService;
import com.app.pulse_music_sb.Request.PaginationDTO;
import com.app.pulse_music_sb.Service.PaginationService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return musicService.findAll(paginationDTO);
    }

    @GetMapping("/{id}")
    public Optional<Music> findById(@PathVariable String id) {
        return musicService.findById(id);
    }

    @PostMapping
    public Music save(@RequestBody RequestCreateMusic requestCreateMusic, MultipartFile image, MultipartFile mp3) {
        return musicService.save(requestCreateMusic, image, mp3);
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
