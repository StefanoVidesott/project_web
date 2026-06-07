package exam_project.main_webapp.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@ComponentScan("exam_project.main_webapp")
public class SecurityConfig {

    // User details manager
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    // Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security chain
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // Authentication
        http.formLogin(c -> c
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/login?loginError")
        );

        // Authorization
        http.authorizeHttpRequests(c -> c
                .requestMatchers("/dashboard").hasAnyRole("ADMIN", "USER_PROVA", "USER_BASIC", "USER_PRO")
                .requestMatchers("/profile").hasAnyRole("USER_PROVA", "USER_BASIC", "USER_PRO")
                .requestMatchers("/change-password").hasAnyRole("USER_PROVA", "USER_BASIC", "USER_PRO")
                .requestMatchers("/training/complete").hasAnyRole("USER_PROVA", "USER_BASIC", "USER_PRO")
                .requestMatchers(POST, "/recensioni").hasAnyRole("USER_PROVA", "USER_BASIC", "USER_PRO")
                .requestMatchers("/trainings").hasAnyRole("USER_PROVA", "USER_BASIC", "USER_PRO")
                .requestMatchers("/upgrade").hasAnyRole("USER_PROVA", "USER_BASIC")
                .requestMatchers("/statistics").hasAnyRole("USER_BASIC", "USER_PRO")
                .requestMatchers("/trainings/default").hasAnyRole("USER_PROVA", "USER_BASIC")
                .requestMatchers("/training/custom").hasAnyRole("USER_PRO")
                .requestMatchers("/trainings/pro").hasAnyRole("USER_PRO")
                .requestMatchers("/dashboard/admin").hasRole("ADMIN")
                .requestMatchers("/admin/statistics").hasRole("ADMIN")
                .requestMatchers("/admin/users").hasRole("ADMIN")
                .requestMatchers("/admin/remove-expired").hasRole("ADMIN")
                .requestMatchers("/dashboard/trial").hasRole("USER_PROVA")
                .requestMatchers("/dashboard/basic").hasRole("USER_BASIC")
                .requestMatchers("/dashboard/pro").hasRole("USER_PRO")
                .requestMatchers(GET, "/recensioni").permitAll()
                .anyRequest().permitAll()
        );

        // Logout
        http.logout(c -> c
                .logoutUrl("/perform_logout")
                .logoutSuccessUrl("/logout")
        );

        // TO DISABLE CSRF PROTECTION
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}