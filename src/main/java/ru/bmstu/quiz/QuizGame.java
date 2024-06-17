package ru.bmstu.quiz;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.bmstu.questions.Question;
import ru.bmstu.utils.MyScanner;

import java.util.ArrayList;

import static ru.bmstu.utils.Colors.*;

@Getter
@Setter
@ToString
@Service
@PropertySource("classpath:questions.properties")
public class QuizGame {
    @Autowired
    private ArrayList<Question> questions;
    @Value("${minPercentageOfCorrectAnswers}")
    private Integer minPercentageOfCorrectAnswers;
    private Integer correctAnswersCounter = 0;

    public void game() {
        String myAnswer;
        final ArrayList<String> colors = new ArrayList<>() {{
            add(ANSI_YELLOW_BOLD_BRIGHT);
            add(ANSI_BLUE_BOLD_BRIGHT);
            add(ANSI_PURPLE_BOLD_BRIGHT);
            add(ANSI_CYAN_BOLD_BRIGHT);
        }};

        for (int i = 0; i < questions.size(); i++) {
            String questionTitle = questions.get(i).getQuestionTitle();
            ArrayList<String> options = questions.get(i).getOptions();
            String correctAnswer = questions.get(i).getAnswer();

            System.out.printf("%sQuestion №%d%s\n", ANSI_GREEN_UNDERLINED, i + 1, ANSI_RESET);
            System.out.printf("\t%s\n", questionTitle);
            System.out.print("Options:\n");
            for (int j = 0; j < options.size(); j++) {
                System.out.printf("\t%s%d%s) %s\n", colors.get(j), (j + 1), ANSI_RESET, options.get(j));
            }
            System.out.printf("Your answer (%stype option number%s): ", ANSI_RED_UNDERLINED, ANSI_RESET);
            myAnswer = MyScanner.getScanner().nextLine();

            if (checkAnswer(myAnswer, options.indexOf(correctAnswer) + 1))
                correctAnswersCounter++;
            System.out.println();
        }

        System.out.printf("%d/%d correct answers\n", correctAnswersCounter, questions.size());
        if (summarizing(correctAnswersCounter)) {
            System.out.printf("%sYou win%s\n", ANSI_GREEN_BOLD_BRIGHT, ANSI_RESET);
        } else {
            System.out.printf("%sYou lose%s\n", ANSI_RED_BOLD_BRIGHT, ANSI_RESET);
        }
    }

    private boolean checkAnswer(String myAnswer, Integer correctAnswerOptionNumber) {
        try {
            return Integer.parseInt(myAnswer) == correctAnswerOptionNumber;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    private boolean summarizing(Integer correctAnswersCounter) {
        return correctAnswersCounter * 100 / questions.size() >= minPercentageOfCorrectAnswers;
    }
}
