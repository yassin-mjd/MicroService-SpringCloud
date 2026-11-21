package net.yassine.billingservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.yassine.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="inventory-service")
public interface InventoryServiceRestClient {
    @GetMapping("/products/{id}")
    @CircuitBreaker(name="inventory-service",fallbackMethod = "getDefaultProduct")
    Product getProduct(@PathVariable Long id);
    default Product getDefaultProduct(Long id,Exception e) {
        return new Product(id,null,0,0);
    }
}
