package syed.abdullah.demo.postgres.integration;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import syed.abdullah.demo.AbstractIntegrationTest;
import syed.abdullah.demo.entity.Product;
import syed.abdullah.demo.entity.Wishlist;
import syed.abdullah.demo.exception.DataNotFoundException;
import syed.abdullah.demo.repository.WishlistRepository;
import syed.abdullah.demo.service.EcommerceService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@Testcontainers
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
//@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EcommerceDatabaseIntegrationTestsForPostgresDB extends AbstractIntegrationTestPostgresDB {

    @Autowired
    private EcommerceService ecommerceService;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Test
    @Transactional
    public void testGetWishlistByCustomerId() {
        Set<Wishlist> wishlist = ecommerceService.getWishlistByCustomerId(103);
        assertEquals(7, wishlist.size());
    }

    @Test
    public void testGetWishlistByInvalidCustomerId() {
        EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class, () -> ecommerceService.getWishlistByCustomerId(999999999));
        assertEquals("Customer not found by ID: 999999999", entityNotFoundException.getMessage());
    }

    @Test
    public void testTotalSalesToday_1() {
        BigDecimal totalSalesToday = ecommerceService.getTotalSalesToday();
        assertEquals(BigDecimal.valueOf(0), totalSalesToday);
    }

    @Test
    public void testTotalSalesToday_2() {
        BigDecimal salesOfDay = ecommerceService.getTotalSalesOfDay(LocalDate.of(2024, 06, 28));
        assertEquals(BigDecimal.valueOf(0), salesOfDay);
    }

    @Test
    public void testTotalSalesOfDay_1() {
        BigDecimal salesOfDay = ecommerceService.getTotalSalesOfDay(LocalDate.of(2004, 11, 17));
        assertEquals(BigDecimal.valueOf(93153.18), salesOfDay);
    }

    @Test
    public void testTotalSalesOfDay_2() {
        BigDecimal salesOfDay = ecommerceService.getTotalSalesOfDay(LocalDate.of(2004, 11, 24));
        assertEquals(BigDecimal.valueOf(125129.55), salesOfDay);
    }

    @Test
    public void testMaxSaleDay_1() {
        LocalDate startDate = LocalDate.of(2004, 1, 1);
        LocalDate endDate = LocalDate.of(2004, 12, 31);
        LocalDate maxSaleDay = ecommerceService.getMaxSaleDay(startDate, endDate);
        LocalDate expected = LocalDate.of(2004, 11, 24);
        assertEquals(expected, maxSaleDay);
    }

    @Test
    public void testMaxSaleDay_2() {
        LocalDate startDate = LocalDate.of(2004, 1, 1);
        LocalDate now = LocalDate.now();
        LocalDate maxSaleDay = ecommerceService.getMaxSaleDay(startDate, now);
        LocalDate expected = LocalDate.of(2004, 11, 24);
        assertEquals(expected, maxSaleDay);
    }

    @Test
    public void testMaxSaleDay_NoMaxSale() {
        LocalDate now = LocalDate.now();
        assertThrows(DataNotFoundException.class, () -> ecommerceService.getMaxSaleDay(now, now));
    }

    @Test
    public void testTopNSellingItemsAllTime() {
        Integer top5 = 5;
        List<Product> products = ecommerceService.getTopNSellingItemsAllTime(top5);
        assertEquals(top5, products.size());
        List<String> expectedProductCodes = List.of("S10_1678", "S10_1949", "S10_2016", "S10_4698", "S10_4757");
        assertThat(products.stream().map(Product::getProductCode).toList(), containsInAnyOrder(expectedProductCodes.toArray()));
    }

    @Test
    public void testTopNSellingItemsLastMonth() {
        Integer top5 = 5;
        List<Product> products = ecommerceService.getTopNSellingItemsLastMonth(top5);
        assertEquals(0, products.size());
    }

    @Test
    public void testTopNSellingItemsBetweenDates() {
        Integer top5 = 5;
        LocalDate startDate = LocalDate.of(2004, 1, 1);
        LocalDate endDate = LocalDate.of(2004, 12, 31);
        List<Product> products = ecommerceService.getTopNSellingItemsBetweenDatesBasedOnNumberOfSales(top5, startDate, endDate);
        assertEquals(5, products.size());
        List<String> expectedProductCodes = List.of("S10_1678", "S10_1949", "S10_2016", "S10_4698", "S10_4757");
        assertThat(products.stream().map(Product::getProductCode).toList(), containsInAnyOrder(expectedProductCodes.toArray()));
    }
}
