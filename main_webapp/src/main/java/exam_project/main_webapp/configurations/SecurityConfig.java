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
                .requestMatchers("/profilo").hasAnyRole("USER_PROVA", "USER_BASIC", "USER_PRO")
                .requestMatchers("/cambioPassword").hasAnyRole("USER_PROVA", "USER_BASIC", "USER_PRO")
                .requestMatchers("/upgrade").hasAnyRole("USER_PROVA", "USER_BASIC")
                .requestMatchers("/adminDashboard").hasRole("ADMIN")
                .requestMatchers("/adminListaUtenti").hasRole("ADMIN")
                .requestMatchers("/rimuoviScaduti").hasRole("ADMIN")
                .requestMatchers("/provaUserDashboard").hasRole("USER_PROVA")
                .requestMatchers("/basicUserDashboard").hasRole("USER_BASIC")
                .requestMatchers("/proUserDashboard").hasRole("USER_PRO")
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