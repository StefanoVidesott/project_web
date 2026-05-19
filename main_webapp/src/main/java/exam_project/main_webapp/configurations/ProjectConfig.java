package exam_project.main_webapp.configurations;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "exam_project.main_webapp")
public class ProjectConfig {
}
