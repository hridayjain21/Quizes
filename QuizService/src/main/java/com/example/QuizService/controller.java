package com.example.QuizService;

import com.example.QuizService.model.quiz;
import com.example.QuizService.service.quizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class controller {

    @Autowired
    quizService quizService;

    @PostMapping("/add")
    public quiz addQuiz(@RequestBody quiz quiz){
        return quizService.addQuiz(quiz);
    }

    @GetMapping("/quizes")
    public List<quiz> getQuiz(){
        return quizService.allQuiz();
    }

    @GetMapping("/getQuiz/{id}")
    public quiz getQuizById(@PathVariable Long id){
        quiz quiz = quizService.getQuizById(id);
        return quiz;
    }
}
