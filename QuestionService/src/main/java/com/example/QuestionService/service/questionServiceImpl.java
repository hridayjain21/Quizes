package com.example.QuestionService.service;

import com.example.QuestionService.clients.quizClient;
import com.example.QuestionService.model.QuestionWithQuizDTO;
import com.example.QuestionService.model.question;
import com.example.QuestionService.model.quiz;
import com.example.QuestionService.repo.questionRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class questionServiceImpl implements questionService {

    RestTemplate restTemplate;
    questionRepo questionRepo;
    quizClient quizClient;
    int attempt;

    @Autowired
    public questionServiceImpl(questionRepo questionRepo,RestTemplate restTemplate,quizClient quizClient){
        this.quizClient = quizClient;
        this.restTemplate = restTemplate;
        this.questionRepo = questionRepo;
    }
    @Override
    public question addQuestion(question q) {
        return questionRepo.save(q);
    }

    @Override
    public List<question> allQuestion() {
        return questionRepo.findAll();
    }

    @Override
    public question getQuestionById(Long id) {
        return questionRepo.findById(id).get();
    }

    @Override
    public List<question> getQuestionsByQuizId(Long id) {
        return  questionRepo.findByQuizId(id);
    }

    @RateLimiter(name = "QuizBreaker",fallbackMethod = "QuizBreakerFallbackMethod")
//    @Retry(name = "QuizBreaker",fallbackMethod = "QuizBreakerFallbackMethod")
    @CircuitBreaker(name = "QuizBreaker",fallbackMethod = "QuizBreakerFallbackMethod")
    @Override
    public QuestionWithQuizDTO getQuestionWithQuiz(Long id) {
//        attempt++;
        System.out.println(attempt);
        QuestionWithQuizDTO questionWithQuizDTO = new QuestionWithQuizDTO();
        question question = getQuestionById(id);
        questionWithQuizDTO.setQuestion(question);
//        quiz quiz = restTemplate.getForObject("http://QUIZ-SERVICE:8085/quiz/getQuiz/"+question.getQuizId()
//                , quiz.class);
        quiz quiz = quizClient.getQuiz(question.getQuizId());
        questionWithQuizDTO.setQuiz(quiz);
        return questionWithQuizDTO;
    }
    public QuestionWithQuizDTO QuizBreakerFallbackMethod(Long id,Exception e){
        return null;
    }
}
