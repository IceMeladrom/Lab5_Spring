package ru.bmstu.questions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
@AllArgsConstructor
public class Question {
    private String questionTitle;
    private ArrayList<String> options;
    private String answer;
}
