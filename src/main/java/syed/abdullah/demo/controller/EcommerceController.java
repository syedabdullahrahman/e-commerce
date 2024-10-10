package syed.abdullah.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import syed.abdullah.demo.dto.MaxSaleDate;
import syed.abdullah.demo.dto.SaleAmount;
import syed.abdullah.demo.entity.Product;
import syed.abdullah.demo.entity.Wishlist;
import syed.abdullah.demo.service.EcommerceService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class EcommerceController {
    private final EcommerceService ecommerceService;

    @Operation(summary = "Retrieve a customer's wish list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of items in the customer's wish list"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("/wishlist/{customerId}")
    public ResponseEntity<Set<Wishlist>> getWishlist(@PathVariable Integer customerId) {
        return ResponseEntity.ok(ecommerceService.getWishlistByCustomerId(customerId));
    }

    @Operation(summary = "Return total sale of today")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return total sale of today"),
    })
    @GetMapping("/sales/today")
    public ResponseEntity<SaleAmount> getTotalSalesToday() {
        return ResponseEntity.ok(new SaleAmount(ecommerceService.getTotalSalesToday()));
    }

    @Operation(summary = "Get max sale day between start-date and end-date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return max sale day between start-date and end-date"),
            @ApiResponse(responseCode = "404", description = "No date found between start-date and end-date")
    })
    @GetMapping("/sales/max-day")
    public ResponseEntity<MaxSaleDate> getMaxSaleDay(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(new MaxSaleDate(ecommerceService.getMaxSaleDay(startDate, endDate)));
    }

    @Operation(summary = "Return top selling items of all time")
    @GetMapping("/sales/top-items/all-time")
    public List<Product> getTopSellingItemsAllTime(@RequestParam(defaultValue = "5") Integer quantity) {
        return ecommerceService.getTopNSellingItemsAllTime(quantity);
    }

    @Operation(summary = "Return top selling items of last month")
    @GetMapping("/sales/top-items/last-month")
    public List<Product> getTopSellingItemsLastMonth(@RequestParam(defaultValue = "5") Integer quantity) {
        return ecommerceService.getTopNSellingItemsLastMonth(quantity);
    }
}