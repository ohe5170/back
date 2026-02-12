package com.app.haetssal_jangteo.controller.user;

import com.app.haetssal_jangteo.dto.UserDTO;
import com.app.haetssal_jangteo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/login/**")
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;

    @GetMapping("check-email")
    @ResponseBody
    public boolean checkEmail(String userEmail){
        return userService.checkEmail(userEmail);
    }

    @GetMapping("join")
    public String goToJoinForm(){
        return "login/join";
    }

    @GetMapping("social-join")
    public String goToKakaoJoinForm(){
        return "login/social-join";
    }


//    리다이렉트 안하면 새로고침할때마다 인서트되버림
    @PostMapping("join")
    public RedirectView join(UserDTO userDTO){
        userService.haetssalJoin(userDTO);
        return new RedirectView("/login/login");
    }
//    리다이렉트 안하면 새로고침할때마다 인서트되버림
    @PostMapping("social-join")
    public RedirectView kakaoJoin(UserDTO userDTO){
        userService.kakaoJoin(userDTO);
        return new RedirectView("/login/login");
    }
}
