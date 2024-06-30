package com.app.pulse_music_sb.Controller;

import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Models.User;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import com.app.pulse_music_sb.Request.DTO.UserDTO;
import com.app.pulse_music_sb.Service.Interface.MusicTypeService;
import com.app.pulse_music_sb.Service.UserService;
import org.springframework.ui.Model;
import com.app.pulse_music_sb.Models.CustomUserDetail;
import com.app.pulse_music_sb.Service.Interface.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private MusicService musicService;
    @Autowired
    private MusicTypeService musicTypeService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model) {
        PaginationDTO paginationDTO = new PaginationDTO(1, 5, "desc", "playCount");
        PaginationDTO musicsPaginationDTO = new PaginationDTO(1, 4, "desc", "playCount");
        PaginationDTO trendingPaginationDTO = new PaginationDTO(1, 10, "desc", "playCount");
        PaginationDTO newPaginationDTO = new PaginationDTO(1, 100, "desc", "createdAt");
        model.addAttribute("carousels", musicService.findAllBy(paginationDTO));
        model.addAttribute("musics", musicService.findAllBy(musicsPaginationDTO));
        model.addAttribute("trending", musicService.findAllBy(trendingPaginationDTO));
        model.addAttribute("news", musicService.findAllBy(newPaginationDTO));

        List<Music> likedMusic = userService.getUserLikedMusic(customUserDetail.getUser().getId());
        if (likedMusic == null) {
            likedMusic = new ArrayList<>(); // Ensure it's not null
        }
        model.addAttribute("likes", likedMusic);
        return "Layouts/Home/index";
    }

    @GetMapping("/chart")
    public String chart(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model) {
        PaginationDTO typePaginationDTO = new PaginationDTO(1, 100, "desc", "createdAt");
        model.addAttribute("types", musicTypeService.findAll(typePaginationDTO));
        model.addAttribute("musics", musicService.findAll());

        List<Music> likedMusic = userService.getUserLikedMusic(customUserDetail.getUser().getId());
        if (likedMusic == null) {
            likedMusic = new ArrayList<>(); // Ensure it's not null
        }
        model.addAttribute("likes", likedMusic);
        return "Layouts/Home/chart";
    }

    @GetMapping("/browse")
    public String browser(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model) {
        PaginationDTO typePaginationDTO = new PaginationDTO(1, 100, "desc", "createdAt");
        model.addAttribute("types", musicTypeService.findAll(typePaginationDTO));
        model.addAttribute("musics", musicService.findAll());

        List<Music> likedMusic = userService.getUserLikedMusic(customUserDetail.getUser().getId());
        if (likedMusic == null) {
            likedMusic = new ArrayList<>(); // Ensure it's not null
        }
        model.addAttribute("likes", likedMusic);
        return "Layouts/Home/browse";
    }


    @GetMapping("/artist")
    public String artist(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model) {
        String[] artistType = {"By name", "By songs"};
        model.addAttribute("types", artistType);
        model.addAttribute("artists", userService.getArtists());

        List<Music> likedMusic = userService.getUserLikedMusic(customUserDetail.getUser().getId());
        if (likedMusic == null) {
            likedMusic = new ArrayList<>(); // Ensure it's not null
        }
        model.addAttribute("zeroLike", likedMusic.size() == 0 ? true : false);
        model.addAttribute("likes", likedMusic);
        return "Layouts/Home/artist";
    }

    @GetMapping("/artist_detail/{id}")
    public String artist_detail(@AuthenticationPrincipal CustomUserDetail customUserDetail,
                                @PathVariable String id,
                                Model model) {
        User artist = userService.findById(id);
        if(artist == null){
            return "redirect:/";
        }
        UserDTO artistDTO = UserDTO.toDTO(artist, userService.getMusicPopulars(artist));
        model.addAttribute("artist", artistDTO);

        List<Music> likedMusic = userService.getUserLikedMusic(customUserDetail.getUser().getId());
        if (likedMusic == null) {
            likedMusic = new ArrayList<>(); // Ensure it's not null
        }
        model.addAttribute("zeroLike", likedMusic.size() == 0 ? true : false);
        model.addAttribute("likes", likedMusic);
        return "Layouts/Home/artist_detail";
    }

    @GetMapping("/track/{id}")
    public String track(@AuthenticationPrincipal CustomUserDetail customUserDetail, @PathVariable String id, Model model) {
        Music music = musicService.findById(id);
        return "Layouts/Home/artist_detail";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model) {
        User user = userService.findById(customUserDetail.getId());
        model.addAttribute("profile", UserDTO.toDTO(user, null));

        List<Music> likedMusic = userService.getUserLikedMusic(customUserDetail.getUser().getId());
        if (likedMusic == null) {
            likedMusic = new ArrayList<>(); // Ensure it's not null
        }
        model.addAttribute("zeroLike", likedMusic.size() == 0 ? true : false);
        model.addAttribute("likes", likedMusic);
        return "Layouts/Home/profile";
    }

    @GetMapping("/scroll_item")
    public String scroll_item(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model) {
        model.addAttribute("musics", musicService.findAll());
        return "Layouts/Home/scroll.item";
    }
}
