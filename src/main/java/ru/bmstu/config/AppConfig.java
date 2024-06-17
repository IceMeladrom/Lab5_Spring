package ru.bmstu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.bmstu.questions.Question;
import ru.bmstu.questions.QuestionsParser;

import java.util.ArrayList;

@Configuration
@ComponentScan("ru.bmstu")
@PropertySource("classpath:questionsParser.properties")
public class AppConfig {
    @Bean
    public ArrayList<Question> getQuestions() {
        QuestionsParser questionsParser = new QuestionsParser();
        ArrayList<Question> questions = questionsParser.parse();
        return questions;
    }
}
