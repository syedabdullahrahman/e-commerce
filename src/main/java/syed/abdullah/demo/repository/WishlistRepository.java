package syed.abdullah.demo.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import syed.abdullah.demo.entity.Wishlist;
import syed.abdullah.demo.entity.WishlistId;

import java.util.Set;

@Repository
@Hidden
public interface WishlistRepository extends JpaRepository<Wishlist, WishlistId>, JpaSpecificationExecutor<Wishlist> {
    Set<Wishlist> findDistinctByCustomerId(Integer customerId);
}