package net.yassine.billingservice;

import net.yassine.billingservice.entities.Bill;
import net.yassine.billingservice.entities.ProductItem;
import net.yassine.billingservice.repository.BillRepository;
import net.yassine.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients

public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
@Bean
    public CommandLineRunner commandLineRunner(BillRepository billRepository, ProductItemRepository productRepository) {
        return args -> {
            List<Long> customersIds = List.of(1L, 2L, 3L);
            List<Long> productIds = List.of(1L, 2L, 3L);
            customersIds.forEach(clientid -> {
                Bill bill = new Bill();
                bill.setBillingDate(new Date());
                bill.setCustomerId(clientid);
                billRepository.save(bill);
                productIds.forEach(productid -> {
                    ProductItem productItem = new ProductItem();
                    productItem.setPrice(1000*Math.random()*600);
                    productItem.setQuantity(new Random().nextInt(20));
                    productItem.setProductId(productid);
                    productItem.setBill(bill);
                    productRepository.save(productItem);
                });
            });
        };
}
}
