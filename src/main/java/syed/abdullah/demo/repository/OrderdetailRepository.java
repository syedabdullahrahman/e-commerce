package syed.abdullah.demo.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import syed.abdullah.demo.entity.Orderdetail;
import syed.abdullah.demo.entity.OrderdetailId;

@Repository
@Hidden
public interface OrderdetailRepository extends JpaRepository<Orderdetail, OrderdetailId>, JpaSpecificationExecutor<Orderdetail> {
}