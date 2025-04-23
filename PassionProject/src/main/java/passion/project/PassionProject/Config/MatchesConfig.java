package passion.project.PassionProject.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import passion.project.PassionProject.Matches;

@Configuration
public class MatchesConfig {
    @Bean
    public Matches match(){
        return new Matches("5","5","2025","5/5/2025","5/10/2025",
                "Pennsauken Country club");
    }
}
