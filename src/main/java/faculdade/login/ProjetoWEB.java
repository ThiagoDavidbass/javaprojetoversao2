package faculdade.login;

import faculdade.login.repository.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@ComponentScan
public class ProjetoWEB {
    public static void main(String[] args) throws Throwable {
        System.out.println("Agenda de shows");
        SpringApplication.run(ProjetoWEB.class, args);
    }
    @Bean
    public CommandLineRunner init(@Autowired ClienteDAO clientes) {
        return args -> {
        };
    }

}

