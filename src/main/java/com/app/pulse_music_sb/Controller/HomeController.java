package com.app.pulse_music_sb.Controller;

import ch.qos.logback.core.model.Model;
import com.app.pulse_music_sb.Auth.Model.CustomUserDetail;
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
        return "Layouts/Home/index";
    }
}
