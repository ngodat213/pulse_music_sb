package com.app.pulse_music_sb.Util.Model;

public record PaginationDTO(
        int page,
        int limit,
        String sortDirection,
        String sortBy
) {
}
