package com.app.pulse_music_sb.Controller;

import com.app.pulse_music_sb.Models.MusicTypeUser;
import com.app.pulse_music_sb.Service.Interface.MusicTypeUserService;
import com.app.pulse_music_sb.Request.PaginationDTO;
import com.app.pulse_music_sb.Service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/musicTypeUsers")
public class MusicTypeUserController {
    @Autowired
    private MusicTypeUserService musicTypeUserService;

    @Autowired
    private PaginationService paginationService;

    @GetMapping
    public Page<MusicTypeUser> findAll(@RequestBody PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        return musicTypeUserService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<MusicTypeUser> findById(@PathVariable String id) {
        return musicTypeUserService.findById(id);
    }

    @PostMapping
    public MusicTypeUser save(@RequestBody MusicTypeUser musicTypeUser) {
        return musicTypeUserService.save(musicTypeUser);
    }

    @PutMapping("/{id}")
    public MusicTypeUser update(@PathVariable String id, @RequestBody MusicTypeUser musicTypeUser) {
        musicTypeUser.setId(id);  // Set the ID to ensure update
        return musicTypeUserService.update(musicTypeUser);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        musicTypeUserService.deleteById(id);
    }
}
