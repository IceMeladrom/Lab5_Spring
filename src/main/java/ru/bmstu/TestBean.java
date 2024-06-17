package ru.bmstu;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@PropertySource("application.properties")
public class TestBean {
    private String name;

    public TestBean(@Value("${name}") String name) {
        this.name = name;
    }

}
