package com.app.pulse_music_sb.Request.Request;

import com.app.pulse_music_sb.Models.Album;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateAlbum {
    private String userId;
    private String title;
    private String description;
    private List<String> albumTypes;
    private List<String> musics;
    private MultipartFile image;

    public Album toEntity(){
        Album res = new Album();
        res.setTitle(title);
        res.setDescription(description);
        res.setPlayCount(0);
        res.setHeartCount(0);
        return res;
    }
}
