package com.app.pulse_music_sb.Models;

import com.app.pulse_music_sb.Common.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Invoice extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "available_plan", referencedColumnName = "id")
    public AvailablePlan availablePlan;
}
