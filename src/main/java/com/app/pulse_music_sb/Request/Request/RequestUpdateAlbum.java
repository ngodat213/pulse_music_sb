package com.app.pulse_music_sb.Request.Request;

import com.app.pulse_music_sb.Models.Album;
import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Models.MusicType;
import com.app.pulse_music_sb.Models.User;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateAlbum {
    private User user;
    private String title;
    private String description;
    private List<MusicType> albumTypes;
    private List<Music> musics;

    public Album toEntity(){
        Album res = new Album();
        res.setUser(user);
        res.setTitle(title);
        res.setDescription(description);
        res.setAlbumTypes(albumTypes);
        res.setMusics(musics);
        return res;
    }
}
