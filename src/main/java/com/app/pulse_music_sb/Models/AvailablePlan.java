package com.app.pulse_music_sb.Models;

import com.app.pulse_music_sb.Common.AbstractEntity;
import com.app.pulse_music_sb.Enums.ExpireType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AvailablePlan extends AbstractEntity {
    @Column(nullable = true)
    private String title;

    @Column(nullable = true)
    private Long price;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private int expire;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private ExpireType expireType;
}
