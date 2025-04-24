package passion.project.PassionProject.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import passion.project.PassionProject.EndPoints.User;

@Configuration
public class UserConfig {

    @Bean
    public User user() {
        return new User("Christian", "Thomas", "cthomas1190", "11-11-1990",
                "cthomas@email.com", "password",
                "Just another user", ":)");
    }
}
