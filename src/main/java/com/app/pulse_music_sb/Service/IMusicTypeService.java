package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.MusicType;
import com.app.pulse_music_sb.Repository.MusicTypeRepository;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IMusicTypeService implements com.app.pulse_music_sb.Service.Interface.IMusicTypeService {
    @Autowired
    private MusicTypeRepository musicTypeRepository;
    @Autowired
    private PaginationService paginationService;

    @Override
    public Page<MusicType> findAllBy(PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        return musicTypeRepository.findAll(pageable);
    }

    @Override
    public List<MusicType> findAll() {
        return musicTypeRepository.findAll();
    }

    @Override
    public Optional<MusicType> findById(String id) {
        return musicTypeRepository.findById(id);
    }

    @Override
    public MusicType save(MusicType musicType) {
        return musicTypeRepository.save(musicType);
    }

    @Override
    public MusicType update(MusicType musicType) {
        return musicTypeRepository.save(musicType);
    }

    @Override
    public void deleteById(String id) {
        musicTypeRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return musicTypeRepository.existsById(id);
    }

    @Override
    public List<MusicType> findByIds(List<String> ids) {
        return ids.stream()
                .map(id -> musicTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("MusicType not found")))
                .collect(Collectors.toList());
    }
}