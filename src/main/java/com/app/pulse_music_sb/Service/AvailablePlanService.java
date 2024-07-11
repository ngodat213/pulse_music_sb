package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.AvailablePlan;
import com.app.pulse_music_sb.Repository.AvailablePlanRepository;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import com.app.pulse_music_sb.Service.Interface.IAvailablePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailablePlanService implements IAvailablePlanService {
    @Autowired
    private PaginationService paginationService;
    @Autowired
    private AvailablePlanRepository availablePlanRepository;

    @Override
    public Page<AvailablePlan> findAllBy(PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        return availablePlanRepository.findAll(pageable);
    }

    @Override
    public List<AvailablePlan> findAll() {
        return availablePlanRepository.findAll();
    }

    @Override
    public Optional<AvailablePlan> findById(String id) {
        return availablePlanRepository.findById(id);
    }

    @Override
    public AvailablePlan save(AvailablePlan availablePlan) {
        return availablePlanRepository.save(availablePlan);
    }

    @Override
    public AvailablePlan update(String id, AvailablePlan availablePlan) {
        return null;
    }

    @Override
    public void deleteById(String id) {
        AvailablePlan availablePlan = availablePlanRepository.findById(id).orElse(null);
        availablePlan.setDeleted(true);
        availablePlanRepository.save(availablePlan);
    }
}
