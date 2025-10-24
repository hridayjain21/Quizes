package com.example.QuizService.service;

import com.example.QuizService.model.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "QUESTION-SERVICE",url = "${question.service.url}")
public interface questionClient{
    @GetMapping("/question/allQuestions/{quizId}")
    public List<Question> getQuestionsByQuizId(@PathVariable Long quizId);
}








