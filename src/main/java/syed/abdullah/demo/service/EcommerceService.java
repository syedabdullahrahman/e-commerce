package syed.abdullah.demo.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syed.abdullah.demo.entity.Customer;
import syed.abdullah.demo.entity.Product;
import syed.abdullah.demo.entity.Wishlist;
import syed.abdullah.demo.repository.CustomerRepository;
import syed.abdullah.demo.repository.WishlistRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class EcommerceService {
    private final WishlistRepository wishlistRepository;
    private final CustomerRepository customerRepository;

    public EcommerceService(WishlistRepository wishlistRepository, CustomerRepository customerRepository) {
        this.wishlistRepository = wishlistRepository;
        this.customerRepository = customerRepository;
    }

    public Set<Wishlist> getWishlistByCustomerId(Integer customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return customer.getWishlists();
        } else {
            throw new EntityNotFoundException("Customer not found by ID: " + customerId);
        }
    }

    public BigDecimal getTotalSalesToday() {
        return BigDecimal.TEN;
    }

    public BigDecimal getTotalSalesOfDay(LocalDate date) {
        return BigDecimal.TEN;
    }

    public LocalDate getMaxSaleDay(LocalDate startDate, LocalDate endDate) {
        return LocalDate.now();
    }

    public List<Product> getTopNSellingItemsAllTime(Integer number) {
        return List.of();
    }

    public List<Product> getTopNSellingItemsLastMonth(Integer number) {
        return List.of();
    }

    public List<Product> getTopNSellingItemsBetweenDates(Integer number, LocalDate startDate, LocalDate endDate) {
        return List.of();
    }
}

