//package passion.project.PassionProject.Config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // disables CSRF in Spring 6+
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll() // Allow all endpoints
//                );
//        return http.build();
//    }
//}
