package com.app.pulse_music_sb.Request.DTO;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetaDTO {
    private String author;
    private String authorlink;
    private String date;
    private String category;
    private String tag;
    private int play;
    private int like;
    private String duration;
}