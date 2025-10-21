package com.example.QuestionService;

//import com.example.QuestionService.Messaging.QuestionMessageProducer;
import com.example.QuestionService.model.QuestionWithQuizDTO;
import com.example.QuestionService.model.question;
import com.example.QuestionService.service.questionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class controller {

    private static final Logger log = LoggerFactory.getLogger(controller.class);
    @Autowired
    questionService questionService;
//    @Autowired
//    QuestionMessageProducer questionMessageProducer;

//    @PostMapping("/add")
//    public question addQuiz(@RequestBody question quiz){
//        question q = questionService.addQuestion(quiz);
//        if (q != null)questionMessageProducer.sendMessage(quiz);
//        return q;
//    }

    @GetMapping("/allQuestions")
    public List<question> getQuestions(){
        log.info("all Questions triggered :: ");
        return questionService.allQuestion();
    }

    @GetMapping("/getQues/{questionId}")
    public QuestionWithQuizDTO getQuestionWithQuizId(@PathVariable Long questionId){
        return questionService.getQuestionWithQuiz(questionId);
    }

    @GetMapping("/allQuestions/{quizId}")
    public List<question> getQuestionsByQuizId(@PathVariable Long quizId){
        return questionService.getQuestionsByQuizId(quizId);
    }

    @GetMapping(value = "/getResult", produces = {"application/json","application/xml"})
    public ResponseEntity<String> getResultFromSomething(@RequestHeader(value = "Accept",required = false)String accept){
        if (accept != null && accept.contains("xml")){
            return ResponseEntity
                    .status(HttpStatus.BAD_GATEWAY)
                    .body("not found");
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("returning json");
    }
}