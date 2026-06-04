package com.jayita.quizapp.controller;

import com.jayita.quizapp.model.Question;
import com.jayita.quizapp.service.QuestionService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getByCat(@PathVariable String category){
        return questionService.getByCategory(category);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question q){
        return questionService.save(q);
    }
    @PostMapping("/addm")
    public List<Question> addMultiple(@RequestBody List<Question> questions){
        return questionService.saveAll(questions);
    }
}
