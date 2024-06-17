package ru.bmstu.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bmstu.questions.Question;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@Component
public class QuizGame {
    @Autowired
    private ArrayList<Question> questions;

    public QuizGame(ArrayList<Question> questions) {
        this.questions = questions;
    }

}
