package com.example.QuestionService;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class config {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
//
//package com.example.QuizService.Messaging;
//
//
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConf {
//
////    @Bean
////    public Queue QuestionsQueue(){
////        return new Queue("QuestionsQueue");
////    }
////
////    @Bean
////    public MessageConverter jsonMessageConvertor(){
////        return new Jackson2JsonMessageConverter();
////    }
////
////    @Bean
////    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
////        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
////        rabbitTemplate.setMessageConverter(jsonMessageConvertor());
////        return rabbitTemplate;
////    }
//
//}
//
//
//
//package com.example.QuizService.Messaging;
//
//import com.example.QuizService.QuizServiceApplication;
//import com.example.QuizService.model.Question;
//import com.example.QuizService.service.quizService;
//import com.netflix.discovery.converters.Auto;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class QuestionMessageConsumer {
//
////    private quizService quizService;
////    private RabbitTemplate rabbitTemplate;
////
////    @Autowired
////    public QuestionMessageConsumer(com.example.QuizService.service.quizService quizService, RabbitTemplate rabbitTemplate) {
////        this.quizService = quizService;
////        this.rabbitTemplate = rabbitTemplate;
////    }
////
////    @RabbitListener(queues = "QuestionsQueue")
////    public void consumeMessage(Question question){
////        System.out.println(question.toString());
////        quizService.updateQuizQuestions(question.getQuizId());
////    }
//}
//
//
//
//package com.example.QuestionService.Messaging;
//
//import com.example.QuestionService.model.question;
//import com.example.QuestionService.model.quiz;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class QuestionMessageProducer {
////    private final RabbitTemplate rabbitTemplate;
////
////    @Autowired
////    public QuestionMessageProducer(RabbitTemplate rabbitTemplate) {
////        this.rabbitTemplate = rabbitTemplate;
////    }
////
////    public void sendMessage(question question){
////        rabbitTemplate.convertAndSend("QuestionsQueue",question);
////
////    }
//}

//package com.example.QuestionService.Messaging;


//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConf {

//    @Bean
//    public Queue QuestionsQueue(){
//        return new Queue("QuestionsQueue");
//    }
//
//    @Bean
//    public MessageConverter jsonMessageConvertor(){
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(jsonMessageConvertor());
//        return rabbitTemplate;
//    }

