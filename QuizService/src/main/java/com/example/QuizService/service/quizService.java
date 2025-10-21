package com.example.QuizService.service;

import com.example.QuizService.model.quiz;

import java.util.List;


public interface quizService {
    quiz addQuiz(quiz q);
    List<quiz> allQuiz();
    quiz getQuizById(Long id);
    void updateQuizQuestions(Long id);
}
