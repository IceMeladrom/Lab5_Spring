package ru.bmstu.questions;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;


import java.io.*;
import java.util.ArrayList;

import org.apache.commons.io.input.BOMInputStream;

@Getter
@Setter
@Component
@PropertySource("classpath:questionsParser.properties")
public class QuestionsParser {
    @Value("${questionsFilename}")
    private String questionsFilename;

    @SneakyThrows
    public ArrayList<Question> parse() {
        ClassPathResource resource = new ClassPathResource("questions.csv");
//        ClassPathResource resource = new ClassPathResource(questionsFilename);
        InputStream inputStream = resource.getInputStream();
        BOMInputStream bomInputStream = new BOMInputStream(inputStream);
        Reader reader = new InputStreamReader(bomInputStream);

        ArrayList<Question> questions = new ArrayList<>();
        Iterable<CSVRecord> csvRecords = CSVFormat.EXCEL.builder().setDelimiter(';').setHeader().setSkipHeaderRecord(true).build().parse(reader);
        for (CSVRecord csvRecord : csvRecords) {
            Question question = getQuestion(csvRecord);
            questions.add(question);
        }

        return questions;
    }

    private Question getQuestion(CSVRecord csvRecord) {
        String questionTitle = csvRecord.get("Question");
        String option1 = csvRecord.get("Option 1");
        String option2 = csvRecord.get("Option 2");
        String option3 = csvRecord.get("Option 3");
        String option4 = csvRecord.get("Option 4");
        String answer = csvRecord.get("Answer");
        ArrayList<String> options = new ArrayList<>() {{
            add(option1);
            add(option2);
            add(option3);
            add(option4);
        }};
        Question question = new Question(questionTitle, options, answer);
        return question;
    }
}
