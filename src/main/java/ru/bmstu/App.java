package ru.bmstu;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bmstu.config.AppConfig;
import ru.bmstu.quiz.QuizGame;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        QuizGame quizGame = context.getBean("quizGame", QuizGame.class);
        quizGame.game();

        context.close();
    }
}
