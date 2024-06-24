package com.app.pulse_music_sb.Repository;

import com.app.pulse_music_sb.Models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, String> {
}
