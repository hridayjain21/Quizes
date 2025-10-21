package com.example.QuizService.service;

import com.example.QuizService.model.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@FeignClient(url = "http://localhost:8086/question",name = "QUESTION-SERVICE")
public interface questionClient{

    @GetMapping("/allQuestions/{quizId}")
    public List<Question> getQuestionsByQuizId(@PathVariable Long quizId);
}








