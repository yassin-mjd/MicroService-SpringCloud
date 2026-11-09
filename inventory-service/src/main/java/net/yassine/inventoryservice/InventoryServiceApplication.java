package net.yassine.inventoryservice;

import net.yassine.inventoryservice.entities.Product;
import net.yassine.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner init(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                    .name("Computer")
                    .price(3200)
                    .quantity(10)
                    .build());
            productRepository.save(Product.builder()
                    .name("Telephone")
                    .price(5000)
                    .quantity(14)
                    .build());
        };
    }

}
