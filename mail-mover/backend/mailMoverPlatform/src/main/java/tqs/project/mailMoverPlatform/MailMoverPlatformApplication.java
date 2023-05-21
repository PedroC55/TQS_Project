package tqs.project.mailMoverPlatform;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("tqs.project.mailMoverPlatform.entities")
@ComponentScan({"tqs.project.mailMoverPlatform", "tqs.project.mailMoverPlatform.controllers", "tqs.project.mailMoverPlatform.services", "tqs.project.mailMoverPlatform.repositories"})
public class MailMoverPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailMoverPlatformApplication.class, args);
	}

}
