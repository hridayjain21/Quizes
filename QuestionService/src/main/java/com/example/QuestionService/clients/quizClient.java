package com.example.QuestionService.clients;

import com.example.QuestionService.model.quiz;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "QUIZ-SERVICE")
//public interface quizClient {
//
//    @GetMapping("/quiz/getQuiz/{id}")
//    quiz getQuiz(@PathVariable Long id);
//}







@FeignClient(url = "http://localhost:8085/quiz",name = "QUIZ-SERVICE")
public interface quizClient {
    @GetMapping("/getQuiz/{id}")
    public quiz getQuiz(@PathVariable Long id);
}













