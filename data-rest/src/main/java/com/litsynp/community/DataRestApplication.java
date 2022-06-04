package com.litsynp.community;

import com.litsynp.community.rest.event.BoardEventHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataRestApplication.class, args);
    }

    @Bean
    BoardEventHandler boardEventHandler() {
        return new BoardEventHandler();
    }
}
