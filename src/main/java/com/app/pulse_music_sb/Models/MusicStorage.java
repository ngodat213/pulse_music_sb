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
public class MusicStorage extends AbstractEntity {
    private String url;
    private String assetId;
    private String publicId;

    @ManyToOne
    @JoinColumn(name = "music_id", nullable = false)
    private Music music;
}