package ru.bmstu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import ru.bmstu.questions.Question;
import ru.bmstu.questions.QuestionsParser;

import java.io.IOException;
import java.util.ArrayList;

import static ru.bmstu.utils.Colors.ANSI_RED;
import static ru.bmstu.utils.Colors.ANSI_RESET;

@Configuration
@ComponentScan("ru.bmstu")
@PropertySource("classpath:questions.properties")
public class AppConfig {
    @Autowired
    Environment env;

    @Bean
    public ArrayList<Question> getQuestions() {
        QuestionsParser questionsParser = new QuestionsParser();
        questionsParser.setQuestionsFilename(env.getProperty("questionsFilename"));
        ArrayList<Question> questions = null;
        try {
            questions = questionsParser.parse();
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            System.exit(1);
        }
        return questions;
    }

}
