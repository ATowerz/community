package com.atz.springboot.controller;

import com.atz.springboot.dto.AccessTokenDTO;
import com.atz.springboot.dto.GithubUser;
import com.atz.springboot.mapper.UserMapper;
import com.atz.springboot.modal.User;
import com.atz.springboot.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/callback")
        public String callback(@RequestParam(name = "code") String code,
                               @RequestParam(name = "state") String state,
                               HttpServletRequest request,
                               HttpServletResponse response
                               ) {
            AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
            accessTokenDTO.setClient_id(clientId);
            accessTokenDTO.setClient_secret(clientSecret);
            accessTokenDTO.setCode(code);
            accessTokenDTO.setRedirect_uri(redirectUri);
            accessTokenDTO.setState(state);
            String accessToken = githubProvider.getAccessToken(accessTokenDTO);
            System.out.println("accesstoken="+accessToken);
            GithubUser githubUser = githubProvider.getUser(accessToken);
            System.out.println(githubUser.getName());
            if (githubUser != null && githubUser.getId() != null) {
                User user= new User();
                String token = UUID.randomUUID().toString();
                user.setAccountId(String.valueOf(githubUser.getId()));
                user.setToken(token);
                user.setName(githubUser.getName());
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModified(user.getGmtCreate());
                userMapper.insert(user);

                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(60 * 60 * 24 * 30 * 6);
                response.addCookie(cookie);
                return "redirect:/";
            }
            else{
            }
            return "index";
    }
}
