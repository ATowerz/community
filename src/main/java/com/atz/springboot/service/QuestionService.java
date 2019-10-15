package com.atz.springboot.service;

import com.atz.springboot.enums.CustomizeErrorCode;
import com.atz.springboot.exception.CustomizeException;
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
        List<Question> questions = questionMapper.list();
        for (Question question : questions) {
            User user = userMapper.findByID(question.getCreator());
            question.setUser(user);
        }
        return questions;
    }

    public PageInfo<Question> listByCreator(int creator, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Question> questions = questionMapper.listByCreator(creator);
        System.out.println(questions.size());
        for (Question question : questions) {
            User user = userMapper.findByID(question.getCreator());
            question.setUser(user);
        }
        PageInfo<Question> questionPageInfo = new PageInfo<>(questions);
        System.out.println(questionPageInfo);
        questionPageInfo.setNavigatePages(5);
        return questionPageInfo;
    }

    public Question getById(Integer id) {
        Question question = questionMapper.findById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.findByID(question.getCreator());
        question.setUser(user);
        return question;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            // 创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.create(question);
        }
        else {
            // 更新

            Question dbQuestion = questionMapper.findById(question.getId());
            if (dbQuestion == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }

            if (dbQuestion.getCreator() != question.getCreator()) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_IS_YOURS);
            }

            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            updateQuestion.setId(question.getId());
            questionMapper.update(updateQuestion);
        }
    }
}
