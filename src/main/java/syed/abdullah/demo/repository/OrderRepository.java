package syed.abdullah.demo.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import syed.abdullah.demo.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
@Hidden
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    @Query(value = "SELECT SUM(OD.priceEach * OD.quantityOrdered) as totalSalesOfDay FROM Order O INNER JOIN Orderdetail OD ON O.id = OD.id.orderNumber WHERE O.orderDate = :date and O.status = 'SHIPPED'")
    Optional<BigDecimal> getTotalSalesOfDay(LocalDate date);

    @Query(value = """
             SELECT
            	o.orderDate as maxSaleDayBetweenDates
            from
            	orders o
            inner join orderdetails o2 on
            	o.orderNumber = o2.orderNumber
            GROUP by
            	o.orderDate
            HAVING
            	SUM(o2.priceEach * o2.quantityOrdered) =
            (
            	SELECT
            		Max(saleOfDay)
            	from
            		(
            		SELECT
            			o.orderDate as OrderDate,
            			SUM(o2.priceEach * o2.quantityOrdered) saleOfDay
            		from
            			orders o
            		inner join orderdetails o2 on
            			o.orderNumber = o2.orderNumber
            		WHERE
            			DATE(o.orderDate) BETWEEN :startDate AND :endDate
            				and o.status = 'SHIPPED'
            			GROUP by
            				o.orderDate) tmp )
            """, nativeQuery = true)
    Optional<LocalDate> getMaxSaleDayBetweenDates(LocalDate startDate, LocalDate endDate);
}