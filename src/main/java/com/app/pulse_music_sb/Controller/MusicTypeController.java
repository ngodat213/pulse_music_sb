package com.app.pulse_music_sb.Controller;

import com.app.pulse_music_sb.Models.MusicType;
import com.app.pulse_music_sb.Service.Interface.MusicTypeService;
import com.app.pulse_music_sb.Util.Model.PaginationDTO;
import com.app.pulse_music_sb.Util.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/musicTypes")
public class MusicTypeController {
    @Autowired
    private MusicTypeService musicTypeService;

    @Autowired
    private PaginationService paginationService;

    @GetMapping
    public Page<MusicType> findAll(@RequestBody PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        return musicTypeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<MusicType> findById(@PathVariable String id) {
        return musicTypeService.findById(id);
    }

    @PostMapping
    public MusicType save(@RequestBody MusicType musicType) {
        return musicTypeService.save(musicType);
    }

    @PutMapping("/{id}")
    public MusicType update(@PathVariable String id, @RequestBody MusicType musicType) {
        musicType.setId(id);  // Set the ID to ensure update
        return musicTypeService.update(musicType);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        musicTypeService.deleteById(id);
    }
}
