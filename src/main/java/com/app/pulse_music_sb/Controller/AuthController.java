package com.app.pulse_music_sb.Controller;

import com.app.pulse_music_sb.Models.CustomUserDetails;
import com.app.pulse_music_sb.Request.Request.RequestRegisterUser;
import com.app.pulse_music_sb.Models.User;
import com.app.pulse_music_sb.Service.MailService;
import com.app.pulse_music_sb.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    /**
     * @layout: <a href="https://localhost:8080/register">...</a>
     * @method: GET
     */
    @GetMapping("/register")
    public String Register(Model model){
        logger.debug("Accessing register page");
        model.addAttribute("registerUser", new RequestRegisterUser());
        return "Layouts/Auth/Register";
    }

    /**
     * @layout: <a href="https://localhost:8080/register">...</a>
     * @method: POST
     */
    @PostMapping("/register_submit")
    public String CreateUser(RequestRegisterUser registerUser){
        logger.debug("Registering new user: {}", registerUser.getEmail());
        userService.register(registerUser);
        return "redirect:/login";
    }

    /**
     * @layout: <a href="https://localhost:8080/login">...</a>
     * @method: GET
     */
    @GetMapping("/login")
    public String Login(){
        logger.debug("Accessing login page");
        return "Layouts/Auth/Login";
    }

    /**
     * @layout: <a href="https://localhost:8080/me">...</a>
     * @method: GET
     */
    @GetMapping("/me")
    public String GetCurrentUser(@AuthenticationPrincipal CustomUserDetails customUserDetail, Model model){
        logger.debug("Accessing current user page");
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
        logger.debug("Accessing change password page for user: {}", customUserDetail.getUsername());
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
        logger.debug("Changing password for user: {}", customUserDetail.getUsername());
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
        logger.debug("Accessing forgot password page");
        return "Layouts/Auth/forgot_password";
    }

    /**
     * @layout: <a href="https://localhost:8080/forgot_password">...</a>
     * @method: POST
     */
    @PostMapping("/forgot_password")
    public String ForgotPassword(@RequestParam("email") String email){
        logger.debug("Processing forgot password for email: {}", email);
        User user = userService.getUserByEmail(email);
        if(user != null){
            String token = userService.GenTokenResetPassword(user);
            String url = "http://localhost:8080/reset_password?token="+token;
            mailService.SendForgotPassword(user.getEmail(), url);
        }
        return "redirect:/forgot_password";
    }

    /**
     * @layout: <a href="https://localhost:8080/reset_password">...</a>
     * @method: GET
     */
    @GetMapping("/reset_password")
    public String ResetPassword(@RequestParam("token") String token,Model model){
        logger.debug("Accessing reset password page with token: {}", token);
        User user = userService.getUserByToken(token);
        if(user != null){
            model.addAttribute("user", user);
        }
        return "Layouts/Auth/reset_password";
    }

    /**
     * @layout: <a href="https://localhost:8080/reset_password">...</a>
     * @method: POST
     */
    @PostMapping("/reset_password_submit")
    public String ResetPassword_Save(@RequestParam("email") String email,
                                     @RequestParam("password") String password,
                                     @RequestParam("confirm_password") String confirmPassword){
        logger.debug("Resetting password for email: {}", email);
        if(!password.equals(confirmPassword)){
            return "redirect:/reset_password?error";
        }
        User user = userService.getUserByEmail(email);
        userService.UpdatePassword(user,password);
        userService.ResetDateForgotPassword(user);
        return "redirect:/login";
    }
}
