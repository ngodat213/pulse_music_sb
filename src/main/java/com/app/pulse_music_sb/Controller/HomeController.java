package com.app.pulse_music_sb.Controller;

import com.app.pulse_music_sb.Request.PaginationDTO;
import org.springframework.ui.Model;
import com.app.pulse_music_sb.Models.CustomUserDetail;
import com.app.pulse_music_sb.Service.Interface.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private MusicService musicService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model) {
        PaginationDTO paginationDTO = new PaginationDTO(1, 5, "desc", "playCount");
        PaginationDTO musicsPaginationDTO = new PaginationDTO(1, 4, "desc", "playCount");
        PaginationDTO trendingPaginationDTO = new PaginationDTO(1, 10, "desc", "playCount");
        model.addAttribute("carousels", musicService.findAll(paginationDTO));
        model.addAttribute("musics", musicService.findAll(musicsPaginationDTO));
        model.addAttribute("trending", musicService.findAll(trendingPaginationDTO));
        return "Layouts/Home/index";
    }
}
