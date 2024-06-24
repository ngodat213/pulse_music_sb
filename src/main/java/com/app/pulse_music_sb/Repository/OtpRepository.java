package com.app.pulse_music_sb.Repository;

import com.app.pulse_music_sb.Models.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OtpRepository extends JpaRepository<Otp, String> {
    Optional<Otp> findByUser_Id(String userId);
}