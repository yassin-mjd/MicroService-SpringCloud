package net.yassine.billingservice.repository;

import net.yassine.billingservice.entities.ProductItem;
import net.yassine.billingservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
    List<ProductItem> findByBillId(Long BillId);
}
