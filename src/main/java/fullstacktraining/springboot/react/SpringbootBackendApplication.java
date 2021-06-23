package fullstacktraining.springboot.react;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Here I have disabled the Spring security Auto configuration so It doesn't block me
// from accessing the frontend, once spring security is configured in the right way the "exclude"
// constrain must be turned off!!!
@SpringBootApplication()
public class SpringbootBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBackendApplication.class, args);
    }


}
