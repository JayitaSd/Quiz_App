package com.jayita.quizapp.service;

import com.jayita.quizapp.dao.QuestionDao;
import com.jayita.quizapp.dao.QuizDao;
import com.jayita.quizapp.model.Question;
import com.jayita.quizapp.model.QuestionWrapper;
import com.jayita.quizapp.model.Quiz;
import com.jayita.quizapp.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizDao quizDao;
    private final QuestionDao questionDao;

    public QuizService(QuizDao quizDao, QuestionDao questionDao) {
        this.quizDao = quizDao;
        this.questionDao = questionDao;
    }

    public ResponseEntity<String> createQuiz(String category, Integer nq, String title) {
        List<Question> q = questionDao.findRandomQByCat(category, nq);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(q);
        quizDao.save(quiz);
        return new ResponseEntity<>("SUCCESS",HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        Optional<Quiz> question = quizDao.findById(id);
        List<Question> qdb = question.get().getQuestions();
        List<QuestionWrapper> qwrapper = new ArrayList<>();

        for(Question q : qdb){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            qwrapper.add(qw);
        }
        return new ResponseEntity<>(qwrapper, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculate(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right=0, i=0;
        for(Response r : responses){
            if(r.getResponse().equals(questions.get(i).getRightAnswer())) right++;
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
