package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.post.Post;
import com.example.demo.post.PostRepository;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Seed sample data so GET /posts works immediately
    @Bean
    CommandLineRunner init(PostRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.saveAll(List.of(
                        new Post("첫 글", "스프링 JPA 빠른 시작"),
                        new Post("Hello", "World")
                ));
            }
        };
    }
}
