package syed.abdullah.demo.controller;

import org.jooq.impl.QOM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import syed.abdullah.demo.entity.Wishlist;
import syed.abdullah.demo.service.EcommerceService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EcommerceController {
    private final EcommerceService ecommerceService;

    public EcommerceController(EcommerceService ecommerceService) {
        this.ecommerceService = ecommerceService;
    }

    @GetMapping("/wishlist/{customerId}")
    public List<Wishlist> getWishlist(@PathVariable Integer customerId) {
        return ecommerceService.getWishlistByCustomerId(customerId);
    }
}