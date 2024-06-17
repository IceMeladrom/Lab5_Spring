package ru.bmstu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import ru.bmstu.questions.Question;
import ru.bmstu.questions.QuestionsParser;

import java.util.ArrayList;

@Configuration
@ComponentScan("ru.bmstu")
@PropertySource("classpath:questionsParser.properties")
public class AppConfig {
    @Autowired
    Environment env;

    @Bean
    public ArrayList<Question> getQuestions() {
        QuestionsParser questionsParser = new QuestionsParser();
        questionsParser.setQuestionsFilename(env.getProperty("questionsFilename"));
        return questionsParser.parse();
    }
}
