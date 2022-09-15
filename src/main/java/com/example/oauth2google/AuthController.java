package com.example.oauth2google;

import com.example.oauth2google.response.LoggedResponse;
import com.example.oauth2google.response.SuccessResponse;
import com.example.oauth2google.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

private final MailService mailService;
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

    @PostMapping("/mail")
    public SuccessResponse successResponse(@RequestParam("email") String email,  @AuthenticationPrincipal OAuth2User principal){
        return mailService.sendData(email, principal);
    }
}
