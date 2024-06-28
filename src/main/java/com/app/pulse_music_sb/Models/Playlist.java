package com.app.pulse_music_sb.Models;

import com.app.pulse_music_sb.Common.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Playlist extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "music_play_list",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id")
    )
    private List<Music> musics;
}