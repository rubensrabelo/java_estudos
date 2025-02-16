package br.com.alurafood.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaClient
public class StartUp {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
