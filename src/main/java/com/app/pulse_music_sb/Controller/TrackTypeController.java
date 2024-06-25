package com.app.pulse_music_sb.Controller;

import com.app.pulse_music_sb.Models.TrackType;
import com.app.pulse_music_sb.Service.Interface.TrackTypeService;
import com.app.pulse_music_sb.Request.PaginationDTO;
import com.app.pulse_music_sb.Service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/trackTypes")
public class TrackTypeController {
    @Autowired
    private TrackTypeService trackTypeService;

    @Autowired
    private PaginationService paginationService;

    @GetMapping
    public Page<TrackType> findAll(@RequestBody PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        return trackTypeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<TrackType> findById(@PathVariable String id) {
        return trackTypeService.findById(id);
    }

    @PostMapping
    public TrackType save(@RequestBody TrackType trackType) {
        return trackTypeService.save(trackType);
    }

    @PutMapping("/{id}")
    public TrackType update(@PathVariable String id, @RequestBody TrackType trackType) {
        trackType.setId(id);  // Set the ID to ensure update
        return trackTypeService.update(trackType);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        trackTypeService.deleteById(id);
    }
}
