package syed.abdullah.demo.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import syed.abdullah.demo.entity.Product;

import java.time.LocalDate;
import java.util.List;

@Repository
@Hidden
public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
    @Query(value = """
                    SELECT p.* from products p
                    WHERE p.productCode in (select * from (
                    select p.productCode
                    from products p 
                    inner join orderdetails o on p.productCode = o.productCode 
                    inner join orders o2 on o.orderNumber = o2.orderNumber 
                    WHERE DATE(o2.orderDate) BETWEEN :startDate AND :endDate and o2.status = 'SHIPPED'
                    Group by p.productCode 
                    ORDER BY Sum(o.quantityOrdered) desc
                    LIMIT :topN) as tmp )
            """, nativeQuery = true)
    List<Product> getTopNProductsBetweenDatesBasedOnNumberOfSales(Integer topN, LocalDate startDate, LocalDate endDate);

    @Query(value = """
            SELECT p.* from products p
            WHERE p.productCode in (select * from (
            select p.productCode
            from products p 
            inner join orderdetails o on p.productCode = o.productCode 
            inner join orders o2 on o.orderNumber = o2.orderNumber 
            WHERE DATE(o2.orderDate) BETWEEN :startDate AND :endDate and o2.status = 'SHIPPED'
            Group by p.productCode 
            ORDER BY Sum(o.quantityOrdered * o.priceEach) desc
            LIMIT :topN) as tmp )
            """, nativeQuery = true)
    List<Product> getTopNProductsBetweenDatesBasedOnTotalSaleAmount(Integer topN, LocalDate startDate, LocalDate endDate);
}