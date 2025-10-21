package com.example.QuestionService.model;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.patterns.TypePatternQuestions;

@Getter
@Setter
public class QuestionWithQuizDTO {
    question question;
    quiz quiz;

    public com.example.QuestionService.model.quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(com.example.QuestionService.model.quiz quiz) {
        this.quiz = quiz;
    }

    public com.example.QuestionService.model.question getQuestion() {
        return question;
    }

    public void setQuestion(com.example.QuestionService.model.question question) {
        this.question = question;
    }
}
