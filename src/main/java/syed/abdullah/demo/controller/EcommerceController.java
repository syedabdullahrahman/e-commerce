package syed.abdullah.demo.controller;

import org.springframework.web.bind.annotation.*;
import syed.abdullah.demo.dto.MaxSaleDate;
import syed.abdullah.demo.dto.SaleAmount;
import syed.abdullah.demo.entity.Product;
import syed.abdullah.demo.entity.Wishlist;
import syed.abdullah.demo.service.EcommerceService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class EcommerceController {
    private final EcommerceService ecommerceService;

    public EcommerceController(EcommerceService ecommerceService) {
        this.ecommerceService = ecommerceService;
    }

    @GetMapping("/wishlist/{customerId}")
    public Set<Wishlist> getWishlist(@PathVariable Integer customerId) {
        return ecommerceService.getWishlistByCustomerId(customerId);
    }

    @GetMapping("/sales/today")
    public SaleAmount getTotalSalesToday() {
        return new SaleAmount(ecommerceService.getTotalSalesToday());
    }

    @GetMapping("/sales/max-day")
    public MaxSaleDate getMaxSaleDay(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return new MaxSaleDate(ecommerceService.getMaxSaleDay(startDate, endDate));
    }

    @GetMapping("/sales/top-items/all-time")
    public List<Product> getTopSellingItemsAllTime(@RequestParam(defaultValue = "5") Integer quantity) {
        return ecommerceService.getTopNSellingItemsAllTime(quantity);
    }

    @GetMapping("/sales/top-items/last-month")
    public List<Product> getTopSellingItemsLastMonth(@RequestParam(defaultValue = "5") Integer quantity) {
        return ecommerceService.getTopNSellingItemsLastMonth(quantity);
    }
}