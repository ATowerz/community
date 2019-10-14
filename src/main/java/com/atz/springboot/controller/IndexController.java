package com.atz.springboot.controller;

import com.atz.springboot.mapper.UserMapper;
import com.atz.springboot.model.Question;
import com.atz.springboot.model.User;
import com.atz.springboot.service.QuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize,
                        @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo){
        PageHelper.startPage(pageNo,pageSize);
        List<Question> questionList =questionService.list();
        PageInfo<Question> questionPageInfo = new PageInfo<>(questionList);
        questionPageInfo.setNavigatePages(5);
//        page.setTotal(total);
//        model.addAttribute("page", page);
        model.addAttribute("questions",questionList);
        model.addAttribute("page",questionPageInfo);
        System.out.println((String)request.getSession().getAttribute("message"));
        return "index";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
