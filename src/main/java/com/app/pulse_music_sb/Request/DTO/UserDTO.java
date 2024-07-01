package com.app.pulse_music_sb.Request.DTO;

import com.app.pulse_music_sb.Models.*;
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
    private List<Album> albums;
    private List<Music> likes;

    public static UserDTO toDTO(User user, List<Music> populars) {
        UserDTO userDTO = new UserDTO();
        userDTO.id = user.getId();
        userDTO.avatarUrl = user.getAvatarUrl();
        userDTO.fullname = user.getFullName();
        userDTO.bio = user.getBio();
        userDTO.tracks = user.getTracks();
        userDTO.types = user.getUserTypes();
        userDTO.playlists = user.getPlayLists();
        userDTO.albums = user.getAlbums();
        userDTO.populars = populars;
        userDTO.likes = user.getUserLiked();
        return userDTO;
    }
}
