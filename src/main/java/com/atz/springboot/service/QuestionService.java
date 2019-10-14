package com.atz.springboot.service;

import com.atz.springboot.mapper.QuestionMapper;
import com.atz.springboot.mapper.UserMapper;
import com.atz.springboot.model.Question;
import com.atz.springboot.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<Question> list() {
    List<Question> questions =  questionMapper.list();
    for (Question question :questions){
        User user = userMapper.findByID(question.getCreator());
        question.setUser(user);
    }
        return questions;
    }
    public PageInfo<Question> listByCreator(int creator, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Question> questions =  questionMapper.listByCreator(creator);
        System.out.println(questions.size());
        for (Question question :questions){
            User user = userMapper.findByID(question.getCreator());
            question.setUser(user);
        }
        PageInfo<Question> questionPageInfo = new PageInfo<>(questions);
        System.out.println(questionPageInfo);
        questionPageInfo.setNavigatePages(5);
        return questionPageInfo;
    }

    public Question getById(Integer id) {
        Question question=questionMapper.listById(id);
        User user=userMapper.findByID(question.getCreator());
        question.setUser(user);
        return question;
    }
}
