package com.app.pulse_music_sb.Controller;

import com.app.pulse_music_sb.Const.ErrorConstants;
import com.app.pulse_music_sb.Const.ToastConstants;
import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Models.MusicType;
import com.app.pulse_music_sb.Models.User;
import com.app.pulse_music_sb.Request.RequestCreateMusic;
import com.app.pulse_music_sb.Request.RequestMusicType;
import com.app.pulse_music_sb.Request.RequestUpdateMusic;
import com.app.pulse_music_sb.Service.Interface.MusicService;
import com.app.pulse_music_sb.Service.Interface.MusicTypeService;
import com.app.pulse_music_sb.Service.UserService;
import com.app.pulse_music_sb.Request.PaginationDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class AdminController {
    @Autowired
    private MusicService musicService;
    @Autowired
    private UserService userService;
    @Autowired
    private MusicTypeService musicTypeService;

    @GetMapping("")
    public String dashboard (){
        return "Layouts/Dashboard/index";
    }

    @GetMapping("/user_table")
    public String userTable (@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "10") int limit,
                             @RequestParam(defaultValue = "desc", name = "sort") String sortDirection,
                             @RequestParam(defaultValue = "createdAt") String sortBy,
                             Model model){
        model.addAttribute("user", new User());
        PaginationDTO paginationDTO = new PaginationDTO(page, limit, sortDirection, sortBy);
        model.addAttribute("users", userService.getAll(paginationDTO));
        return "Layouts/Dashboard/user_table";
    }

    @GetMapping("/music_type_table")
    public String musicTypeTable (@RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(defaultValue = "desc", name = "sort") String sortDirection,
                                  @RequestParam(defaultValue = "createdAt") String sortBy,
                                  Model model){
        PaginationDTO paginationDTO = new PaginationDTO(page, limit, sortDirection, sortBy);
        model.addAttribute("type", new RequestMusicType());
        model.addAttribute("types", musicTypeService.findAll(paginationDTO));
        return "Layouts/Dashboard/music_type_table";
    }

    @GetMapping("/music_table")
    public String musicTable (@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int limit,
                              @RequestParam(defaultValue = "desc", name = "sort") String sortDirection,
                              @RequestParam(defaultValue = "createdAt") String sortBy,
                              Model model){
        PaginationDTO paginationDTO = new PaginationDTO(page, limit, sortDirection, sortBy);
        model.addAttribute("music", new RequestCreateMusic());
        model.addAttribute("artists", userService.getAll(paginationDTO));
        model.addAttribute("musics", musicService.findAllBy(paginationDTO));
        model.addAttribute("types", musicTypeService.findAll(paginationDTO));
        return "Layouts/Dashboard/music_table";
    }
    // API
    // ----------------------==MUSIC TYPE==---------------------- //
    @GetMapping("/music_type_table/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMusicTypeById(@PathVariable String id) {
        Optional<MusicType> musicType = musicTypeService.findById(id);
        if (musicType.isPresent()) {
            return ResponseEntity.ok(Map.of("status", "success", "musicType", musicType.get()));
        } else {
            return ResponseEntity.status(404).body(Map.of("status", "error", "message", "MusicType not found"));
        }
    }

    @PostMapping("/music_type_table")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createMusicType(@RequestBody MusicType musicType) {
        MusicType savedMusicType = musicTypeService.save(musicType);
        return ResponseEntity.ok(Map.of("status", "success", "musicType", savedMusicType));
    }

    @PutMapping("/music_type_table/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateMusicType(@PathVariable String id, @RequestBody MusicType musicTypeDetails) {
        Optional<MusicType> optionalMusicType = musicTypeService.findById(id);
        if (optionalMusicType.isPresent()) {
            MusicType musicType = optionalMusicType.get();
            musicType.setTypeName(musicTypeDetails.getTypeName());
            MusicType updatedMusicType = musicTypeService.save(musicType);
            return ResponseEntity.ok(Map.of("status", "success", "musicType", updatedMusicType));
        } else {
            return ResponseEntity.status(404).body(Map.of("status", "error", "message", "MusicType not found"));
        }
    }

    @DeleteMapping("/music_type_table/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteMusicType(@PathVariable String id) {
        if (musicTypeService.existsById(id)) {
            musicTypeService.deleteById(id);
            return ResponseEntity.ok(Map.of("status", "success", "message", "MusicType deleted successfully"));
        } else {
            return ResponseEntity.status(404).body(Map.of("status", "error", "message", "MusicType not found"));
        }
    }
    // ----------------------==MUSIC==---------------------- //
    @GetMapping("/music_table/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMusic(@PathVariable String id) {
        Music music = musicService.findById(id);
        if (music != null) {
            return ResponseEntity.ok(Map.of("status", "success", "music", music));
        } else {
            return ResponseEntity.status(404).body(Map.of("status", "error", "message", "Music not found"));
        }
    }

    @PutMapping("/music_table/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> editLocation(@PathVariable String id,
                                                            @Validated @ModelAttribute RequestUpdateMusic request,
                                                            @RequestParam("image") MultipartFile image,
                                                            @RequestParam("mp3") MultipartFile mp3) {
        Music existingMusic = musicService.findById(id);
        if (existingMusic != null) {
            musicService.update(id, request, image, mp3);
            return ResponseEntity.ok(Map.of("status", "success", "message", "Music updated successfully"));
        } else {
            return ResponseEntity.status(404).body(Map.of("status", "error", "message", "Music not found"));
        }
    }

    @PostMapping("/music_table")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createMusic(@Valid @ModelAttribute RequestCreateMusic createMusic,
                                                           @RequestParam("image") MultipartFile image,
                                                           @RequestParam("mp3") MultipartFile mp3) {
        Map<String, Object> response = new HashMap<>();
        // Validate file types
        if (!image.getContentType().startsWith("image/")) {
            response.put("status", "error");
            response.put("message", "Unsupported image format");
            return ResponseEntity.badRequest().body(response);
        }

        if (!mp3.getContentType().equals("audio/mpeg")) {
            response.put("status", "error");
            response.put("message", "Unsupported audio format");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            // Process your file and form data
            musicService.save(createMusic, image, mp3);
            response.put("status", "success");
            response.put("message", "Music created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // ----------------------==USER==---------------------- //
    @PostMapping("/user_lock/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> userLock(@PathVariable("id") String userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean isAdmin = userService.handleLockUser(userId);
            if (!isAdmin) {
                response.put(ErrorConstants.status, ErrorConstants.statusError);
                response.put(ErrorConstants.message, ToastConstants.cantLockUser);
                return ResponseEntity.badRequest().body(response);
            }
            response.put(ErrorConstants.status,ErrorConstants.statusSuccess);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put(ErrorConstants.status, ErrorConstants.statusError);
            response.put(ErrorConstants.message, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/create_user")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createUser(@Valid @ModelAttribute User user) {
        Map<String, Object> response = new HashMap<>();
        if (userService.existsByEmail(user.getEmail())) {
            response.put(ErrorConstants.status, ErrorConstants.statusError);
            response.put(ErrorConstants.message, ErrorConstants.emailExits);
            return ResponseEntity.badRequest().body(response);
        }
        userService.createUser(user);
        response.put(ErrorConstants.status,ErrorConstants.statusSuccess);
        return ResponseEntity.ok(response);
    }
}
