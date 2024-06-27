package syed.abdullah.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import syed.abdullah.demo.Wishlist;
import syed.abdullah.demo.entity.WishlistId;

public interface WishlistRepository extends JpaRepository<Wishlist, WishlistId>, JpaSpecificationExecutor<Wishlist> {
}