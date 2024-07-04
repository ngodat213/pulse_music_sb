package com.app.pulse_music_sb.Models;

import com.app.pulse_music_sb.Common.AbstractEntity;
import com.app.pulse_music_sb.Request.DTO.MetaDTO;
import com.app.pulse_music_sb.Request.DTO.PlaylistTrackDTO;
import com.app.pulse_music_sb.Request.DTO.ThumbDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Music extends AbstractEntity {
    @Size(max = 200)
    @Column(length = 200)
    private String title;

    @Size(max = 2000)
    @Column(length = 2000)
    private String description;
    private int duration;
    private int playCount;
    private int heartCount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private CloudStorage image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mp3_id", referencedColumnName = "id")
    private CloudStorage mp3;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_id", referencedColumnName = "id")
    private Credit credit;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "music_type_id", referencedColumnName = "id")
    private MusicType musicType;

    @ManyToMany(mappedBy = "userLiked")
    private List<User> likedByUsers;

    @ManyToMany(mappedBy = "musics")
    private List<Playlist> playlists;

    public int getLikedCount(){
        if(likedByUsers != null && !likedByUsers.isEmpty()){
            return likedByUsers.size();
        }
        return 0;
    }

    public String getFormatTime(){
        int minutes = duration / 60;
        int seconds = duration % 60;
        return String.format("%d:%d", minutes, seconds);
    }

    public PlaylistTrackDTO toDTO() {
        ThumbDTO thumbDTO = new ThumbDTO();
        thumbDTO.setSrc(image.getUrl());

        MetaDTO metaDTO = new MetaDTO();
        metaDTO.setAuthor(getUser().getFullName());
        metaDTO.setAuthorlink("#");
        metaDTO.setDate(getFormattedCreatedAt());
        metaDTO.setCategory(musicType != null ? musicType.getTypeName() : "no");
        metaDTO.setPlay(playCount);
        metaDTO.setLike(heartCount);
        metaDTO.setDuration(getFormatTime());

        PlaylistTrackDTO res = new PlaylistTrackDTO();
        res.setId(getId());
        res.setTitle(getTitle());
        res.setExcept(getDescription());
        res.setLink("#");
        res.setThumb(thumbDTO);
        res.setSrc(getMp3().getUrl());
        res.setMeta(metaDTO);
        return res;
    }
}
