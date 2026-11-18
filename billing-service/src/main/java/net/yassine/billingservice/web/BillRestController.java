package net.yassine.billingservice.web;

import net.yassine.billingservice.entities.Bill;
import net.yassine.billingservice.feign.CustomerRestClient;
import net.yassine.billingservice.feign.InventoryServiceRestClient;
import net.yassine.billingservice.model.Customer;
import net.yassine.billingservice.repository.BillRepository;
import net.yassine.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private InventoryServiceRestClient inventoryServiceRestClient;
    @GetMapping("/bills/{id}")
    public Bill getBillById(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerRestClient.findCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(pi -> {
            pi.setProduct(
                    inventoryServiceRestClient.getProduct(pi.getProductId()));
        });
        return bill;
    }
}
