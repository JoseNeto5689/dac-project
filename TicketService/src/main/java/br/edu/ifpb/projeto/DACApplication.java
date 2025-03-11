package br.edu.ifpb.projeto;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@EnableRabbit
@SpringBootApplication
public class DACApplication {

    public static void main(String[] args) {
        SpringApplication.run(DACApplication.class, args);
    }

}
