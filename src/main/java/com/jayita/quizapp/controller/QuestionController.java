package com.jayita.quizapp.controller;

import com.jayita.quizapp.model.Question;
import com.jayita.quizapp.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/allQuestions")
    public List<Question> getAll(){
        return questionService.getAllQuestions();
    }
    @GetMapping("category/{category}")
    public List<Question> getByCat(@PathVariable String category){
        return questionService.getByCategory(category);
    }
    @PostMapping("/create")
    public Question create(@RequestBody Question q){
        return questionService.save(q);
    }
}
