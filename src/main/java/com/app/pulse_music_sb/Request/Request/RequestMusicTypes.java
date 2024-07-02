package com.app.pulse_music_sb.Request.Request;

import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Models.MusicType;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestMusicTypes {
    private MusicType musicType;
    private List<Music> musics;
}
