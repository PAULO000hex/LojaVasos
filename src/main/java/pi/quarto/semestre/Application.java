package pi.quarto.semestre;

import javax.persistence.Entity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "pi.quarto.semestre.models")
@ComponentScan(basePackages = {"pi.*"})
@EnableJpaRepositories(basePackages= {"pi.quarto.semestre.repositories"})
@EnableTransactionManagement

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
