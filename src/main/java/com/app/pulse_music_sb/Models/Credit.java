package com.app.pulse_music_sb.Models;

import com.app.pulse_music_sb.Common.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Credit extends AbstractEntity {
    private String performedBy;
    private String writtenBy;
    private String producedBy;
}
