package com.example.external;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExternalApplication {

    public static void main(String[] args) {
        try {
            Dotenv dotenv = Dotenv.load();
            System.out.println("Environment file loaded");
        } catch (DotenvException dotenvException) {
            System.out.println("Environment file not found");
        }
        SpringApplication.run(ExternalApplication.class, args);
    }



}
