package syed.abdullah.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import syed.abdullah.demo.entity.Orderdetail;
import syed.abdullah.demo.entity.OrderdetailId;

public interface OrderdetailRepository extends JpaRepository<Orderdetail, OrderdetailId>, JpaSpecificationExecutor<Orderdetail> {
}