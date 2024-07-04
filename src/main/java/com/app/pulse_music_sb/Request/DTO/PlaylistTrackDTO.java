package com.app.pulse_music_sb.Request.DTO;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistTrackDTO {
    private String id;
    private String title;
    private String except;
    private String link;
    private String src;
    private ThumbDTO thumb;
    private MetaDTO meta;
}