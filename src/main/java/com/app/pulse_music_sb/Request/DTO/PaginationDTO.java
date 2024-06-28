package com.app.pulse_music_sb.Request.DTO;

public record PaginationDTO(
        int page,
        int limit,
        String sortDirection,
        String sortBy
) {
}
