package syed.abdullah.demo.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syed.abdullah.demo.entity.Customer;
import syed.abdullah.demo.entity.Product;
import syed.abdullah.demo.entity.Wishlist;
import syed.abdullah.demo.exception.DataNotFoundException;
import syed.abdullah.demo.repository.CustomerRepository;
import syed.abdullah.demo.repository.OrderRepository;
import syed.abdullah.demo.repository.ProductRepository;
import syed.abdullah.demo.repository.WishlistRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class EcommerceService {
    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public EcommerceService(WishlistRepository wishlistRepository, ProductRepository productRepository, OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
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
        return getTotalSalesOfDay(LocalDate.now());
    }

    public BigDecimal getTotalSalesOfDay(LocalDate date) {
        return orderRepository.getTotalSalesOfDay(date).orElse(BigDecimal.ZERO);
    }

    public LocalDate getMaxSaleDay(LocalDate startDate, LocalDate endDate) {
        Optional<LocalDate> maxSaleDayBetweenDates = orderRepository.getMaxSaleDayBetweenDates(startDate, endDate);
        if(maxSaleDayBetweenDates.isPresent()){
            return maxSaleDayBetweenDates.get();
        } else {
            throw new DataNotFoundException(String.format("No date found between %s and %s",startDate,endDate));
        }
    }

    /**
     * Return top N selling items of all time (based on total sale amount).
     * @param number
     * @return
     */
    public List<Product> getTopNSellingItemsAllTime(Integer number) {
        return getTopNSellingItemsBetweenDatesBasedOnTotalSaleAmount(number, LocalDate.MIN, LocalDate.now());
    }

    /**
     * return top N selling items of the last month (based on number of sales).
     * @param number
     * @return
     */

    public List<Product> getTopNSellingItemsLastMonth(Integer number) {
        LastMonth lastMonth = getLastMonth();
        return getTopNSellingItemsBetweenDatesBasedOnNumberOfSales(number, lastMonth.startDate, lastMonth.endDate);
    }
    record LastMonth(LocalDate startDate, LocalDate endDate){}
    private LastMonth getLastMonth() {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        YearMonth yearMonth = YearMonth.from(date);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
        return new LastMonth(firstDayOfMonth, lastDayOfMonth);
    }

    public List<Product> getTopNSellingItemsBetweenDatesBasedOnNumberOfSales(Integer number, LocalDate startDate, LocalDate endDate) {
        List<Product> basedOnNumberOfSales = productRepository.getTopNProductsBetweenDatesBasedOnNumberOfSales(number, startDate, endDate);
        return basedOnNumberOfSales;
    }

    public List<Product> getTopNSellingItemsBetweenDatesBasedOnTotalSaleAmount(Integer number, LocalDate startDate, LocalDate endDate) {
        List<Product> basedOnTotalSaleAmount = productRepository.getTopNProductsBetweenDatesBasedOnTotalSaleAmount(number, startDate, endDate);
        return basedOnTotalSaleAmount;
    }
}

