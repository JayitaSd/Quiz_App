package com.jayita.quizapp.service;

import com.jayita.quizapp.dao.QuestionDao;
import com.jayita.quizapp.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionDao questionDao;

    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public Question save(Question q) {
        return questionDao.save(q);
    }
}
