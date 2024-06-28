package syed.abdullah.demo;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
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

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//todo: TestContainers
public class EcommerceDatabaseIntegrationTests {

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
    public void testTotalSalesToday() {
        BigDecimal totalSalesToday = ecommerceService.getTotalSalesToday();
        assertEquals(BigDecimal.valueOf(0), totalSalesToday);
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
        List<String> expectedProductCodes = List.of("S18_3232", "S18_1342", "S700_4002", "S18_3856", "S50_1341");
        assertThat(products.stream().map(Product::getProductCode).toList(), containsInAnyOrder(expectedProductCodes));
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
        List<Product> products = ecommerceService.getTopNSellingItemsBetweenDates(top5, startDate, endDate);
        assertEquals(5, products.size());
        List<String> expectedProductCodes = List.of("S18_3232", "S18_1662", "S12_1108", "S700_2610", "S18_3856");
        assertThat(products.stream().map(Product::getProductCode).toList(), containsInAnyOrder(expectedProductCodes));
    }
}
