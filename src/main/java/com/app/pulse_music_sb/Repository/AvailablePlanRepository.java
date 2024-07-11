package com.app.pulse_music_sb.Repository;

import com.app.pulse_music_sb.Models.AvailablePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailablePlanRepository extends JpaRepository<AvailablePlan, String> {
}
