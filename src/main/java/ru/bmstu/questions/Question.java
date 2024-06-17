package ru.bmstu.questions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;


@Getter
@Setter
@AllArgsConstructor
@ToString
public class Question {
    private String questionTitle;
    private ArrayList<String> options;
    private String answer;
}
