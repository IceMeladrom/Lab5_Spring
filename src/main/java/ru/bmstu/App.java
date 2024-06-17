package ru.bmstu;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.bmstu.config.AppConfig;
import ru.bmstu.questions.Question;
import ru.bmstu.questions.QuestionsParser;
import ru.bmstu.quiz.QuizGame;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

//        QuestionsParser questionsParser = context.getBean("questionsParser", QuestionsParser.class);
//        ArrayList<Question> questions = questionsParser.parse();

        QuizGame quizGame = context.getBean("quizGame", QuizGame.class);
        System.out.println(quizGame);

        context.close();
    }
}
