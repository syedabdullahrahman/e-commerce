package syed.abdullah.demo.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import syed.abdullah.demo.entity.Product;

@Repository
@Hidden
public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
}