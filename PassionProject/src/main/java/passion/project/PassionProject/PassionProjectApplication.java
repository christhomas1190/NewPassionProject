package passion.project.PassionProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
@EnableJpaRepositories
public class PassionProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(PassionProjectApplication.class, args);
	}

}
