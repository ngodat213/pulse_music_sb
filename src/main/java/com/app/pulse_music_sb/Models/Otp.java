package com.app.pulse_music_sb.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_otp")
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private LocalDateTime expiredAt;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
}