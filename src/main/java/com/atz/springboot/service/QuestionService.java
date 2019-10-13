package com.atz.springboot.service;

import com.atz.springboot.dto.QuestionDTO;
import com.atz.springboot.mapper.QuestionMapper;
import com.atz.springboot.mapper.UserMapper;
import com.atz.springboot.modal.Question;
import com.atz.springboot.modal.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
