package ru.vortex.physics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;

@SpringBootApplication
public class MinecraftPayment {
    public static void main(String[] args) {
        System.out.println("Я ЗАПУЩЕН, СУЧКА");
        SpringApplication.run(MinecraftPayment.class, args);
    }
}