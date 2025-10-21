package com.example.QuizService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
    private Long id;
    private String question;
    private Long quizId;

    @Override
    public String toString() {
        //this shows whole question string
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", quizId=" + quizId +
                '}';
    }
}