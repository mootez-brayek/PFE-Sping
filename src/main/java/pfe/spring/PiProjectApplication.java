package pfe.spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "pfe.spring.repositury")
@EnableMongoRepositories(basePackages = "pfe.spring.mongo_repo")
public class PiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiProjectApplication.class, args);
	}





}



