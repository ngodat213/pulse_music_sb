package com.app.pulse_music_sb.Repository;

import com.app.pulse_music_sb.Models.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, String> {
}