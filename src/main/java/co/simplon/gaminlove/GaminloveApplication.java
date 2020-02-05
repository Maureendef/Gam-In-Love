package co.simplon.gaminlove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 // TODO je ne crois pas que ce soit necessaire de le mettre ici aussi (deja dans SwaggerConfig)
public class GaminloveApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaminloveApplication.class, args);
	}

}
