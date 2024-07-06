package com.example.bookmyshow;

import com.example.bookmyshow.models.BaseModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookmyShowApplication {

    public static void main(String[] args) {
        BaseModel b=new BaseModel();
        int x=b.getId();
        SpringApplication.run(BookmyShowApplication.class, args);
    }

}
