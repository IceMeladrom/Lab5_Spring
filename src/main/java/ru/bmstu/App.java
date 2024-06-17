package ru.bmstu;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.bmstu.questions.Question;
import ru.bmstu.questions.QuestionsParser;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        TestBean testBean = context.getBean("testBean", TestBean.class);
        System.out.println(testBean.getName());

        QuestionsParser questionsParser = context.getBean("questionsParser", QuestionsParser.class);
        ArrayList<Question> questions = questionsParser.parse();

        context.close();
    }
}
