package com.app.pulse_music_sb.Controller;

import com.app.pulse_music_sb.Models.CustomUserDetails;
import com.app.pulse_music_sb.Request.Request.RequestRegisterUser;
import com.app.pulse_music_sb.Models.User;
import com.app.pulse_music_sb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    /**
     * @layout: <a href="https://localhost:8080/register">...</a>
     * @method: GET
     */
    @GetMapping("/register")
    public String Register(Model model){
        model.addAttribute("registerUser", new RequestRegisterUser());
        return "Layouts/Auth/Register";
    }

    /**
     * @layout: <a href="https://localhost:8080/register">...</a>
     * @method: POST
     */
    @PostMapping("/register_submit")
    public String CreateUser(RequestRegisterUser registerUser){
        userService.register(registerUser);
        return "redirect:/login";
    }

    /**
     * @layout: <a href="https://localhost:8080/login">...</a>
     * @method: GET
     */
    @GetMapping("/login")
    public String Login(){
        return "Layouts/Auth/Login";
    }

    /**
     * @layout: <a href="https://localhost:8080/me">...</a>
     * @method: GET
     */
    @GetMapping("/me")
    public String GetCurrentUser(@AuthenticationPrincipal CustomUserDetails customUserDetail, Model model){
        User user = userService.currentUser();
        model.addAttribute("user", user);
        return "Layouts/Auth/Me";
    }

    /**
     * @layout: <a href="https://localhost:8080/change_password">...</a>
     * @method: GET
     */
    @GetMapping("/change_password")
    public String  ChangePassword(@AuthenticationPrincipal CustomUserDetails customUserDetail, Model model){
        model.addAttribute("user", customUserDetail.getUser());
        return "Layout/Auth/changepassword";
    }

    /**
     * @layout: <a href="https://localhost:8080/change_password">...</a>
     * @method: POST
     */
    @PostMapping("change_password")
    public String SavePassword(@AuthenticationPrincipal CustomUserDetails customUserDetail,
                               @RequestParam("oldpassword") String oldPassword,
                               @RequestParam("newpassword") String newpassword){
        User user = customUserDetail.getUser();
        if(userService.checkOldPassword(user, oldPassword)){
            userService.UpdatePassword(user, newpassword);
            return "redirect:/change_password?done";
        }else{
            return "redirect:/change_password?error";
        }
    }

    /**
     * @layout: <a href="https://localhost:8080/forgot_password">...</a>
     * @method: GET
     */
    @GetMapping("forgot_password")
    public String ForgotPassword(){
        return "Layouts/Auth/forgot_password";
    }

    /**
     * @layout: <a href="https://localhost:8080/forgot_password">...</a>
     * @method: POST
     */
    @PostMapping("/forgot_password")
    public String ForgotPassword(@RequestParam("email") String email){
        User user = userService.getUserByEmail(email);
        if(user != null){
            userService.GenTokenResetPassword(user);
        }
        return "redirect:/forgot_password";
    }

    /**
     * @layout: <a href="https://localhost:8080/reset_password">...</a>
     * @method: GET
     */
    @GetMapping("/reset_password")
    public String ResetPassword(@RequestParam("token") String token,Model model){
        User user = userService.getUserByToken(token);
        if(user != null){
            model.addAttribute("user", user);
        }
        return "Layout/Auth/reset_password";
    }

    /**
     * @layout: <a href="https://localhost:8080/reset_password">...</a>
     * @method: POST
     */
    @PostMapping("/resetpassword")
    public String ResetPassword_Save(@RequestParam("username") String username,
                                     @RequestParam("password") String password){
        User user = userService.getUserByUsername(username);
        userService.UpdatePassword(user,password);
        userService.ResetDateForgotPassword(user);
        return "redirect:/login";
    }
}
