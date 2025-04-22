package passion.project.PassionProject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchesConfig {
    @Bean
    public Matches match(){
        return new Matches(12345L,"5","10", "2025","5/5/2025",
                "5/10/2025","Pennsauken Country club");
    }
}
