package com.example.QuizService.service;

import com.example.QuizService.model.quiz;
import com.example.QuizService.repo.quizRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Service
public class quizServiceImpl implements quizService{

    quizRepo quizRepo;
    questionClient questionClient;
    int attempt = 0;

    @Autowired
    public quizServiceImpl(quizRepo quizRepo, questionClient questionClient) {
        this.quizRepo = quizRepo;
        this.questionClient = questionClient;
    }

    @Override
    public quiz addQuiz(quiz q) {
        return quizRepo.save(q);
    }

    @Override
    public List<quiz> allQuiz() {
        List<quiz> list = quizRepo.findAll();
        for(quiz q : list){
            q.setQuestions(questionClient.getQuestionsByQuizId(q.getId()));
        }
        return list;
    }

    @RateLimiter(name = "QuizServiceLimiter")
//    @Retry(name = "QuestionRetry",fallbackMethod = "QuestionBreakerFallback")
//    @CircuitBreaker(name = "QuestionBreaker",fallbackMethod = "QuestionBreakerFallback")
    @Override
    public quiz getQuizById(Long id) {
//        attempt++;
//        System.out.printf("attempt -> "+String.valueOf(attempt));
        quiz q = quizRepo.findById(id).get();
        q.setQuestions(questionClient.getQuestionsByQuizId(id));
        return q;
    }

    @Override
    public void updateQuizQuestions(Long id) {
        quiz q = quizRepo.findById(id).get();
        int x = q.getNoOfQuestions();
        ++x;
        q.setNoOfQuestions(x);
        quizRepo.save(q);
    }

    public quiz QuestionBreakerFallback(Long id,Exception e){
        quiz q = quizRepo.findById(id).get();
        q.setQuestions(null);
        return q;
    }
}
