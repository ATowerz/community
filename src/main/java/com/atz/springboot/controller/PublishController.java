package com.atz.springboot.controller;

import com.atz.springboot.mapper.QuestionMapper;
import com.atz.springboot.mapper.UserMapper;
import com.atz.springboot.modal.Question;
import com.atz.springboot.modal.User;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/publish")
    public String publish(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
        Cookie[] cookies = request.getCookies();
        User user = new User();
        if(cookies!=null){
        for(Cookie cookie :cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user =userMapper.findByToken(token);
                if (user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        }
        if (user == null) {
            request.setAttribute("message", "请登录!");
            return "index";
        }
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(Question question,HttpServletRequest request){
        User user =(User)request.getSession().getAttribute("user");
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}
