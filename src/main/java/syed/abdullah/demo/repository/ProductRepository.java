package syed.abdullah.demo.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.domain.Limit;
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
                    SELECT p FROM Product p 
                     WHERE p.productCode IN (
                         SELECT p.productCode FROM Order o
                         JOIN o.orderdetails od
                         JOIN od.productCode p
                         WHERE o.orderDate BETWEEN :startDate AND :endDate 
                         AND o.status = 'SHIPPED'
                         GROUP BY p.productCode 
                         ORDER BY SUM(od.quantityOrdered),p.productCode DESC
                     )
            """)
    List<Product> getTopNProductsBetweenDatesBasedOnNumberOfSales(LocalDate startDate, LocalDate endDate, Limit topN);

    @Query(value = """
            SELECT p FROM Product p
                                 WHERE p.productCode IN (
                                     SELECT p.productCode FROM Order o
                                     JOIN o.orderdetails od
                                     JOIN od.productCode p
                                     WHERE o.orderDate BETWEEN :startDate AND :endDate
                                     AND o.status = 'SHIPPED'
                                     GROUP BY p.productCode
                                     ORDER BY SUM(od.quantityOrdered * od.priceEach),p.productCode DESC
                                 )
            """)
    List<Product> getTopNProductsBetweenDatesBasedOnTotalSaleAmount(LocalDate startDate, LocalDate endDate,Limit topN);

}