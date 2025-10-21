package com.example.QuestionService.repo;

import com.example.QuestionService.model.question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class questionCustomRepoImpl implements questionCustomRepo{
    @Autowired
    EntityManager entityManager;

    @Override
    public List<question> findByQuizId(Long quizId) {
        TypedQuery<question> query = entityManager.createQuery("select i from question i where i.quizId = :quizId",question.class)
                .setParameter("quizId",quizId);
        return query.getResultList();
    }
}
