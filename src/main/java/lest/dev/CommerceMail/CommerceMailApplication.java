package lest.dev.CommerceMail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CommerceMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommerceMailApplication.class, args);
	}

}
