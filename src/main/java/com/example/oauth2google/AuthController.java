package com.example.oauth2google;

import com.example.oauth2google.response.LoggedResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @GetMapping("/loggedIn")
    public LoggedResponse logged(@AuthenticationPrincipal OAuth2User principal){
        LoggedResponse loggedResponse = new LoggedResponse();
        if(ObjectUtils.isEmpty(principal)){
            loggedResponse.setLogged(false);
        }else{
            loggedResponse.setLogged(true);
        }
        return loggedResponse;
    }
}
