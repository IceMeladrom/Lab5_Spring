package ru.bmstu.questions;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

@Getter
@Setter
@Component
public class QuestionsParser {
    private String questionsFilename;

    public ArrayList<Question> parse() throws IOException, IllegalArgumentException {
        ArrayList<Question> questions = new ArrayList<>();
        Iterable<CSVRecord> csvRecords = null;
        ClassPathResource resource = new ClassPathResource(questionsFilename);
        try {
            InputStream inputStream = resource.getInputStream();
            BOMInputStream.Builder bomInputStream = BOMInputStream.builder().setInputStream(inputStream);
            Reader reader = new InputStreamReader(bomInputStream.get());

            csvRecords = CSVFormat.EXCEL.builder().setDelimiter(';').setHeader().setSkipHeaderRecord(true).build().parse(reader);
        } catch (IOException e) {
            throw new IOException(e);
        }

        for (CSVRecord csvRecord : csvRecords) {
            Question question = null;
            try {
                question = getQuestion(csvRecord);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Headers in a csv file in this format [Question;Option 1;Option 2;Option 3;Option 4;Answer]\nEdit your csv file and try again!");
            }
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
        return new Question(questionTitle, options, answer);
    }
}
