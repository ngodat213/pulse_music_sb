package com.app.pulse_music_sb.Request.DTO;

import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Models.User;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {
    List<Music> musics;
    List<User> artists;
}
