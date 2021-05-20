package fascinating.pitj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@SpringBootApplication
public class PitjApplication {

	public static void main(String[] args) {
		SpringApplication.run(PitjApplication.class, args);
	}

}
