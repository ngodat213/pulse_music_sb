package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.AdsAudio;
import com.app.pulse_music_sb.Models.CloudStorage;
import com.app.pulse_music_sb.Repository.AdsAudioRepository;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import com.app.pulse_music_sb.Request.Request.RequestCreateAdsAudio;
import com.app.pulse_music_sb.Service.Interface.IAdsAudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class AdsAudioService implements IAdsAudioService {
    @Autowired
    private PaginationService paginationService;
    @Autowired
    private AdsAudioRepository adsAudioRepository;
    @Autowired
    private CloudService cloudService;

    @Override
    public Page<AdsAudio> findAllBy(PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        return adsAudioRepository.findAll(pageable);
    }

    @Override
    public List<AdsAudio> findAll() {
        return adsAudioRepository.findAll();
    }

    @Override
    public Optional<AdsAudio> findById(String id) {
        return adsAudioRepository.findById(id);
    }

    @Override
    public AdsAudio save(RequestCreateAdsAudio request, MultipartFile audio) {
        CloudStorage mp3Music = cloudService.uploadFile(audio, true);
        AdsAudio adsAudio = request.toEntity();
        adsAudio.setAdsAudio(mp3Music);
        return adsAudioRepository.save(adsAudio);
    }

    @Override
    public void deleteById(String id) {
        AdsAudio adsAudio = adsAudioRepository.findById(id).orElse(null);
        adsAudio.setDeleted(true);
        adsAudioRepository.save(adsAudio);
    }
}
