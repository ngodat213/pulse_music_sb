package com.app.pulse_music_sb.Request.Request;

import com.app.pulse_music_sb.Models.Album;
import lombok.*;

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

    public Album toEntity(){
        Album res = new Album();
        res.setTitle(title);
        res.setDescription(description);
        return res;
    }
}
