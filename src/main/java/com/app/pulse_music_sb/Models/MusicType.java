package com.app.pulse_music_sb.Models;

import com.app.pulse_music_sb.Common.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class MusicType extends AbstractEntity {
    private String typeName;

    @ManyToMany(mappedBy = "musicTypes")
    private List<Music> music;

    @ManyToMany(mappedBy = "userTypes")
    private List<User> users;
}