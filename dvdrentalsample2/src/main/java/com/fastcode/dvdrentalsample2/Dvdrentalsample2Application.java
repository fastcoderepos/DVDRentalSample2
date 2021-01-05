package com.fastcode.dvdrentalsample2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.fastcode.dvdrentalsample2", "org.springframework.versions" })
public class Dvdrentalsample2Application {

    public static void main(String[] args) {
        SpringApplication.run(Dvdrentalsample2Application.class, args);
    }
}
