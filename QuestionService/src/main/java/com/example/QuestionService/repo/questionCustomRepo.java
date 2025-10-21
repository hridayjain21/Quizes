package com.example.QuestionService.repo;

import com.example.QuestionService.model.question;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface questionCustomRepo {
    List<question> findByQuizId(Long quizId);
}
