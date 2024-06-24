package com.app.pulse_music_sb.Models;

import com.app.pulse_music_sb.Common.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Music extends AbstractEntity {
    private int time;
    private int playCount;
    private int heartCount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "music")
    private List<Track> tracks;

    @OneToMany(mappedBy = "music")
    private List<TrackType> trackTypes;

    @OneToMany(mappedBy = "music")
    private List<MusicStorage> musicStorages;

    @OneToMany(mappedBy = "music")
    private List<Playlist> playlists;
}
