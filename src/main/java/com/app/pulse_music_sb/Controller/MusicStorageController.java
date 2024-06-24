package com.app.pulse_music_sb.Controller;

import com.app.pulse_music_sb.Models.MusicStorage;
import com.app.pulse_music_sb.Service.Interface.MusicStorageService;
import com.app.pulse_music_sb.Util.Model.PaginationDTO;
import com.app.pulse_music_sb.Util.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/musicStorages")
public class MusicStorageController {
    @Autowired
    private MusicStorageService musicStorageService;

    @Autowired
    private PaginationService paginationService;

    @GetMapping
    public Page<MusicStorage> findAll(@RequestBody PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        return musicStorageService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<MusicStorage> findById(@PathVariable String id) {
        return musicStorageService.findById(id);
    }

    @PostMapping
    public MusicStorage save(@RequestBody MusicStorage musicStorage) {
        return musicStorageService.save(musicStorage);
    }

    @PutMapping("/{id}")
    public MusicStorage update(@PathVariable String id, @RequestBody MusicStorage musicStorage) {
        musicStorage.setId(id);  // Set the ID to ensure update
        return musicStorageService.update(musicStorage);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        musicStorageService.deleteById(id);
    }
}
