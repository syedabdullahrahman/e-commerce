package syed.abdullah.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syed.abdullah.demo.entity.Wishlist;
import syed.abdullah.demo.repository.WishlistRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EcommerceService {
    private WishlistRepository wishlistRepository;

    public EcommerceService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wishlist> getWishlistByCustomerId(Integer customerId) {
        return wishlistRepository.findAllByCustomerId(customerId);
    }

}

