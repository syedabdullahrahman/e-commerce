package syed.abdullah.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import syed.abdullah.demo.entity.Productline;

public interface ProductlineRepository extends JpaRepository<Productline, String>, JpaSpecificationExecutor<Productline> {
}