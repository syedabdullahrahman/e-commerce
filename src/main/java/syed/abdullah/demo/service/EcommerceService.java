package syed.abdullah.demo.service;

import io.micrometer.observation.annotation.Observed;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Limit;
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
@AllArgsConstructor
@Slf4j
@Observed(name = "e-commerce-service",contextualName = "EcommerceService")
public class EcommerceService {
    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public Set<Wishlist> getWishlistByCustomerId(Integer customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return customer.getWishlists();
        } else {
            throw new EntityNotFoundException("Customer not found by ID: " + customerId);
        }
    }

    @Observed(name = "getTotalSalesToday",contextualName = "getTotalSalesToday")
    public BigDecimal getTotalSalesToday() {
        BigDecimal totalSalesOfDay = getTotalSalesOfDay(LocalDate.now());
        log.info("Total sales of today: " + totalSalesOfDay);
        return totalSalesOfDay;
    }

    @Observed(name = "getTotalSalesOfDay",contextualName = "getTotalSalesOfDay")
    public BigDecimal getTotalSalesOfDay(LocalDate date) {
        BigDecimal bigDecimal = orderRepository.getTotalSalesOfDay(date).orElse(BigDecimal.ZERO);
        log.info("Total sales of "+ date + ": " + bigDecimal);
        return bigDecimal;
    }

    @Observed(name = "getMaxSaleDay",contextualName = "getMaxSaleDay")
    public LocalDate getMaxSaleDay(LocalDate startDate, LocalDate endDate) {
        Optional<LocalDate> maxSaleDayBetweenDates = orderRepository.getMaxSaleDayBetweenDates(startDate, endDate);
        if(maxSaleDayBetweenDates.isPresent()){
            LocalDate localDate = maxSaleDayBetweenDates.get();
            log.info("Max sales day between "+ startDate + " -to- " + endDate +": "+ localDate);
            return localDate;
        } else {
            throw new DataNotFoundException(String.format("No date found between %s and %s",startDate,endDate));
        }
    }

    /**
     * Return top N selling items of all time (based on total sale amount).
     * @param number
     * @return
     */
    @Observed(name = "getTopNSellingItemsAllTime",contextualName = "getTopNSellingItemsAllTime")
    public List<Product> getTopNSellingItemsAllTime(Integer number) {
        return getTopNSellingItemsBetweenDatesBasedOnTotalSaleAmount(number, LocalDate.EPOCH, LocalDate.now());
    }

    /**
     * return top N selling items of the last month (based on number of sales).
     * @param number
     * @return
     */

    @Observed(name = "getTopNSellingItemsLastMonth",contextualName = "getTopNSellingItemsLastMonth")
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

    @Observed(name = "getTopNSellingItemsBetweenDatesBasedOnNumberOfSales",contextualName = "getTopNSellingItemsBetweenDatesBasedOnNumberOfSales")
    public List<Product> getTopNSellingItemsBetweenDatesBasedOnNumberOfSales(Integer number, LocalDate startDate, LocalDate endDate) {
        List<Product> basedOnNumberOfSales = productRepository.getTopNProductsBetweenDatesBasedOnNumberOfSales(startDate, endDate, Limit.of(number));
        return basedOnNumberOfSales;
    }

    @Observed(name = "getTopNSellingItemsBetweenDatesBasedOnTotalSaleAmount",contextualName = "getTopNSellingItemsBetweenDatesBasedOnTotalSaleAmount")
    public List<Product> getTopNSellingItemsBetweenDatesBasedOnTotalSaleAmount(Integer number, LocalDate startDate, LocalDate endDate) {
        List<Product> basedOnTotalSaleAmount = productRepository.getTopNProductsBetweenDatesBasedOnTotalSaleAmount(startDate, endDate, Limit.of(number));
        return basedOnTotalSaleAmount;
    }
}

