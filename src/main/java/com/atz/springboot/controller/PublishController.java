package com.atz.springboot.controller;

import com.atz.springboot.mapper.QuestionMapper;
import com.atz.springboot.mapper.UserMapper;
import com.atz.springboot.model.Question;
import com.atz.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/publish")
    public String publish(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {

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
