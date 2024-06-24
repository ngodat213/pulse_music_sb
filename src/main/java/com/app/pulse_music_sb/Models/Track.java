package com.app.pulse_music_sb.Models;

import com.app.pulse_music_sb.Common.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Track extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "music_id", nullable = false)
    private Music music;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int timeCount;
    private String title;
    private String description;
}