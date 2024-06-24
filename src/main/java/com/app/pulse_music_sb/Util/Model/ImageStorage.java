package com.app.pulse_music_sb.Util.Model;

import com.app.pulse_music_sb.Common.AbstractEntity;
import com.app.pulse_music_sb.Models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ImageStorage extends AbstractEntity {
    public ImageStorage(String url, String assetId, String publicId) {
        this.url = url;
        this.assetId = assetId;
        this.publicId = publicId;
    }

    private String url;
    private String assetId;
    private String publicId;

    @OneToOne(mappedBy = "avatar")
    private User user;
}