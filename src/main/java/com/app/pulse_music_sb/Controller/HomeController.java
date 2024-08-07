package com.app.pulse_music_sb.Controller;

import com.app.pulse_music_sb.Models.CustomUserDetails;
import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Models.User;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import com.app.pulse_music_sb.Request.DTO.SearchDTO;
import com.app.pulse_music_sb.Request.DTO.UserDTO;
import com.app.pulse_music_sb.Request.Request.RequestCreateMusic;
import com.app.pulse_music_sb.Service.AlbumService;
import com.app.pulse_music_sb.Service.Interface.IMusicTypeService;
import com.app.pulse_music_sb.Service.MusicService;
import com.app.pulse_music_sb.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.app.pulse_music_sb.Service.Interface.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private IMusicService IMusicService;
    @Autowired
    private IMusicTypeService musicTypeService;
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private MusicService musicService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal CustomUserDetails customUserDetail, Model model) {
        PaginationDTO paginationDTO = new PaginationDTO(1, 5, "desc", "playCount");
        PaginationDTO musicsPaginationDTO = new PaginationDTO(1, 4, "desc", "playCount");
        PaginationDTO trendingPaginationDTO = new PaginationDTO(1, 10, "desc", "playCount");
        PaginationDTO newPaginationDTO = new PaginationDTO(1, 100, "desc", "createdAt");
        model.addAttribute("carousels", IMusicService.findAllBy(paginationDTO));
        model.addAttribute("musics", IMusicService.findAllBy(musicsPaginationDTO));
        model.addAttribute("trending", IMusicService.findAllBy(trendingPaginationDTO));
        model.addAttribute("news", IMusicService.findAllBy(newPaginationDTO));

        List<Music> likedMusic = userService.getUserLikedMusic(customUserDetail.getId());
        model.addAttribute("profile", UserDTO.toDTO(customUserDetail.getUser(), null));
        model.addAttribute("likes", likedMusic != null ? likedMusic : new ArrayList<>());
        return "Layouts/Home/index";
    }

    @GetMapping("/chart")
    public String chart(@AuthenticationPrincipal CustomUserDetails customUserDetail, Model model) {
        PaginationDTO typePaginationDTO = new PaginationDTO(1, 100, "desc", "createdAt");
        model.addAttribute("types", musicTypeService.findAllBy(typePaginationDTO));
        model.addAttribute("musics", IMusicService.findAll());

        List<Music> likedMusic = userService.getUserLikedMusic(customUserDetail.getId());
        model.addAttribute("profile", UserDTO.toDTO(customUserDetail.getUser(), null));
        model.addAttribute("likes", likedMusic != null ? likedMusic : new ArrayList<>());
        return "Layouts/Home/chart";
    }

    @GetMapping("/browse")
    public String browser(@AuthenticationPrincipal CustomUserDetails customUserDetail, Model model) {
        PaginationDTO typePaginationDTO = new PaginationDTO(1, 100, "desc", "createdAt");
        model.addAttribute("types", musicTypeService.findAllBy(typePaginationDTO));
        model.addAttribute("musics", IMusicService.findAll());

        List<Music> likedMusic = userService.getUserLikedMusic(customUserDetail.getId());
        model.addAttribute("profile", UserDTO.toDTO(customUserDetail.getUser(), null));
        model.addAttribute("likes", likedMusic != null ? likedMusic : new ArrayList<>());
        return "Layouts/Home/browse";
    }


    @GetMapping("/artist")
    public String artist(@AuthenticationPrincipal CustomUserDetails customUserDetail, Model model) {
        String[] artistType = {"By name", "By songs"};
        model.addAttribute("types", artistType);
        model.addAttribute("artists", userService.getArtists());

        List<Music> likedMusic = userService.getUserLikedMusic(customUserDetail.getId());
        model.addAttribute("profile", UserDTO.toDTO(customUserDetail.getUser(), null));
        model.addAttribute("likes", likedMusic != null ? likedMusic : new ArrayList<>());
        return "Layouts/Home/artist";
    }

    @GetMapping("/artist_detail/{id}")
    public String artist_detail(@AuthenticationPrincipal CustomUserDetails customUserDetail,
                                @PathVariable String id,
                                Model model) {
        User artist = userService.findById(id);
        if(artist == null){
            return "redirect:/";
        }
        UserDTO artistDTO = UserDTO.toDTO(artist, userService.getMusicPopulars(artist));
        model.addAttribute("artist", artistDTO);

        List<Music> likedMusic = userService.getUserLikedMusic(customUserDetail.getId());
        model.addAttribute("profile", UserDTO.toDTO(customUserDetail.getUser(), null));
        model.addAttribute("likes", likedMusic != null ? likedMusic : new ArrayList<>());
        return "Layouts/Home/artist_detail";
    }

    @GetMapping("/track/{id}")
    public String track(@AuthenticationPrincipal CustomUserDetails customUserDetail, @PathVariable String id, Model model) {
        return "Layouts/Home/artist_detail";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal CustomUserDetails customUserDetail, Model model) {
        model.addAttribute("profile", UserDTO.toDTO(customUserDetail.getUser(), null));
        model.addAttribute("likes", userService.getUserLikedMusic(customUserDetail.getId()));
        model.addAttribute("playlists", userService.getPlaylistsByUserId(customUserDetail.getId()));
        model.addAttribute("albums", userService.getAlbumsByUserId(customUserDetail.getId()));
        model.addAttribute("tracks", userService.getTracksByUserId(customUserDetail.getId()));
        model.addAttribute("music", new RequestCreateMusic());
        model.addAttribute("types", musicTypeService.findAll());
        return "Layouts/Home/profile";
    }

    @GetMapping("/album/{id}")
    public String albumDetail(@PathVariable String id, Model model) {
        model.addAttribute("album", albumService.findById(id).get());
        return "Layouts/Home/album.detail";
    }

    @GetMapping("/scroll_item")
    public String scroll_item(@AuthenticationPrincipal CustomUserDetails customUserDetail, Model model) {
        model.addAttribute("musics", IMusicService.findAll());
        return "Layouts/Home/scroll.item";
    }

    @GetMapping("/get_first_music")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getFirstMusic() {
        return ResponseEntity.ok(Map.of("status", "success", "mepPlaylistTracks", IMusicService.getPlaylistTrack()));
    }

    @GetMapping("/get_next_music")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getNextMusic() {
        return ResponseEntity.ok(Map.of("status", "success", "randomMusic", IMusicService.getRandomTrack()));
    }

    @PostMapping("/profile_change")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                             @RequestParam("username") String username,
                                                             @RequestParam(value = "avatar", required = false) MultipartFile avatar) {
        User isUpdated = userService.updateAvatarAndFullName(customUserDetails.getId() ,username, avatar);
        return ResponseEntity.ok().body(Map.of("success", isUpdated != null ? true : false));
    }

    @PostMapping("/favorite_change")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addFavorite(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                             @RequestParam("musicId") String musicId) {
        return ResponseEntity.ok().body(Map.of("success", userService.setFavorite(customUserDetails.getId(), musicId)));
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam(value = "query", defaultValue = "") String query) {
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setArtists(userService.searchArtist(query));
        searchDTO.setMusics(musicService.searchMusic(query));
        return ResponseEntity.ok().body(Map.of("success", searchDTO));
    }
}
