package com.example.QuestionService.service;

import com.example.QuestionService.model.QuestionWithQuizDTO;
import com.example.QuestionService.model.question;
import java.util.List;


public interface questionService {
    question addQuestion(question q);
    List<question> allQuestion();
    question getQuestionById(Long id);
    List<question> getQuestionsByQuizId(Long id);
    QuestionWithQuizDTO getQuestionWithQuiz(Long id);
}
