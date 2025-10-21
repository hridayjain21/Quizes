package com.example.QuizService.repo;

import com.example.QuizService.model.quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface quizRepo extends JpaRepository<quiz,Long> {
}
