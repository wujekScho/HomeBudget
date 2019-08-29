package pl.piotrschodzinski.homebudget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.piotrschodzinski.homebudget.entity.User;
import pl.piotrschodzinski.homebudget.service.UserService;

@SpringBootApplication
public class HomeBudgetApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(HomeBudgetApplication.class, args);
        UserService userService = ctx.getBean(UserService.class);
        User user = new User("admin", "admin@gmail.com", "admin");
        userService.addRegularUser(user);
    }
}
