package com.vashchenko.micro.edu.menuservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MenuServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MenuServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
