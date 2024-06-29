package com.app.pulse_music_sb.Request.DTO;

import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Models.MusicType;
import com.app.pulse_music_sb.Models.Playlist;
import com.app.pulse_music_sb.Models.User;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String fullname;
    private String avatarUrl;
    private String bio;
    private List<Music> tracks;
    private List<MusicType> types;
    private List<Playlist> playlists;
    private List<Music> populars;

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.id = user.getId();
        userDTO.avatarUrl = user.getAvatarUrl();
        userDTO.fullname = user.getFullName();
        userDTO.bio = user.getBio();
        userDTO.tracks = user.getTracks();
        userDTO.types = user.getUserTypes();
        userDTO.playlists = user.getPlayLists();
        return userDTO;
    }


}
