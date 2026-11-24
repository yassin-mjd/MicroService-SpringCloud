package net.yassine.customerservice;

import net.yassine.customerservice.config.CustomConfigParams;
import net.yassine.customerservice.entities.Customer;
import net.yassine.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomConfigParams.class)
public class CutomerServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(CutomerServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder()
                    .name("Mohammed").email("med@gmail.com").build());
            customerRepository.save(Customer.builder()
                    .name("Amine").email("imane@gmail.com").build());

        };
    }

}
