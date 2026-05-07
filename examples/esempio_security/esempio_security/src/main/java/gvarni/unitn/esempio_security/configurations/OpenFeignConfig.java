package gvarni.unitn.esempio_security.configurations;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
@ComponentScan("gvarni.unitn.esempio_security")
@EnableFeignClients(basePackages = "gvarni.unitn.esempio_security")
public class OpenFeignConfig {

}
