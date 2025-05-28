package passion.project.PassionProject.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import passion.project.PassionProject.Entity.User;

@Configuration
public class UserConfig {

    @Bean
    public User user() {
        return new User(
                "Christian", "Thomas", "cthomas1190", "11-11-1990",
                "cthomas@email.com", "password",
                "Just another user", ":)",
                "Jazz, Rock",  // musicGenre
                "Yes",         // drinksAlcohol
                "No",          // gambles
                10,            // handicap
                "Casual"       // intensity
        );
    }
}



