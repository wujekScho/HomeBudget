package pl.piotrschodzinski.homebudget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HomeBudgetApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(HomeBudgetApplication.class, args);
    }
}
