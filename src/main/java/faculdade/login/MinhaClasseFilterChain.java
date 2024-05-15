package faculdade.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;

//https://spring.io/guides/gs/securing-web/
@Configuration
@EnableWebSecurity
public class MinhaClasseFilterChain {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                .requestMatchers("/", "/home").permitAll()
                .anyRequest().authenticated())
                .headers(headers -> headers.frameOptions().disable())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("thiago")
                .password("12345")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @GetMapping("/echo")
    public Map<String, Object> echoRequest(HttpServletRequest request, @RequestBody(required = false) String body) {
        HttpHeaders headers = Collections.list(request.getHeaderNames())
                .stream()
                .collect(HttpHeaders::new, (h, hn) -> h.add(hn, request.getHeader(hn)), HttpHeaders::putAll);

        return Map.of(
                "method", request.getMethod(),
                "path", request.getRequestURI(),
                "headers", headers,
                "body", body);
    }

    @PostMapping("/postEndpoint")
    public ResponseEntity<String> handlePostRequest(@RequestBody String body) {
        System.out.println(body);
        return ResponseEntity.ok("Received POST request with body: " + body);
    }
}
