package com.vashchenko.micro.edu.menuservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity
@EnableCaching
public class MenuServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MenuServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
