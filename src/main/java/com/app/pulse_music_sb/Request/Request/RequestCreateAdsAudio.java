package com.app.pulse_music_sb.Request.Request;

import com.app.pulse_music_sb.Models.AdsAudio;
import com.app.pulse_music_sb.Models.Album;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateAdsAudio {
    private String title;

    public AdsAudio toEntity(){
        AdsAudio res = new AdsAudio();
        res.setTitle(title);
        return res;
    }
}
