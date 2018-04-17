package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			log.info("Loading data starting");
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer", java.util.Arrays.asList("jack","jacky","jocko")));
			repository.save(new Customer("Chloe", "O'Brian",java.util.Arrays.asList("c","cob")));
			
			repository.save(new Customer("Kim", "Bauer",java.util.Arrays.asList("kimmyk","kk","ok","kb")));
			repository.save(new Customer("David", "Palmer", java.util.Arrays.asList("DaveyBoy","d")));
			repository.save(new Customer("Michelle", "Dessler", java.util.Arrays.asList("mikey","mitch","m")));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			repository.findById(1L)
				.ifPresent(customer -> {
					log.info("Customer found with findById(1L):");
					log.info("--------------------------------");
					log.info(customer.toString());
					log.info("");
				});

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			// 	log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}
