package tqs.project.mailMoverPlatform;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"tqs.project.mailMoverPlatform.entities"})
public class MailMoverPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailMoverPlatformApplication.class, args);
	}

}
