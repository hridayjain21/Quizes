package com.example.QuestionService.repo;

import com.example.QuestionService.model.question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface questionRepo extends JpaRepository<question,Long>,questionCustomRepo {}

