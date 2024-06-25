package com.app.pulse_music_sb.Request;

import com.app.pulse_music_sb.Models.MusicType;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateMusic {
    private String title;
    private String description;
    private MultipartFile image;
    private MultipartFile mp3;
    private List<MusicType> types;
    private String userId;
}
