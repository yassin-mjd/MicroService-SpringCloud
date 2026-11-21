package net.yassine.billingservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.yassine.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="customer-service")
public interface CustomerRestClient {
    @GetMapping("/customers/{id}")
    //affiche le cache
    @CircuitBreaker(name="customer-service",fallbackMethod="getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);

    default Customer getDefaultCustomer(Long id,Exception exception){
        exception.printStackTrace();//donne un erreur dans le demarrage
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName("default customer");
        customer.setEmail("default@gmail.com");
        return customer;
    }
}
