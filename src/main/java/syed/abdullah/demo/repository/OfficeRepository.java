package syed.abdullah.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import syed.abdullah.demo.entity.Office;

public interface OfficeRepository extends JpaRepository<Office, String>, JpaSpecificationExecutor<Office> {
}