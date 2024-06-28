package syed.abdullah.demo;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import syed.abdullah.demo.entity.Wishlist;
import syed.abdullah.demo.exception.DataNotFoundException;
import syed.abdullah.demo.repository.WishlistRepository;
import syed.abdullah.demo.service.EcommerceService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public void testTotalSalesToday(){
        BigDecimal totalSalesToday = ecommerceService.getTotalSalesToday();
        assertEquals(BigDecimal.valueOf(0),totalSalesToday);
    }

    @Test
    public void testTotalSalesOfDay_1(){
        BigDecimal salesOfDay = ecommerceService.getTotalSalesOfDay(LocalDate.of(2004,11,17));
        assertEquals(BigDecimal.valueOf(93153.18),salesOfDay);
    }

    @Test
    public void testTotalSalesOfDay_2(){
        BigDecimal salesOfDay = ecommerceService.getTotalSalesOfDay(LocalDate.of(2004,11,24));
        assertEquals(BigDecimal.valueOf(125129.55),salesOfDay);
    }

    @Test
    public void testMaxSaleDay_1(){
        LocalDate maxSaleDay = ecommerceService.getMaxSaleDay(LocalDate.of(2004,1, 1), LocalDate.of(2004,12,31));
        assertEquals(LocalDate.of(2004,11,24),maxSaleDay);
    }

    @Test
    public void testMaxSaleDay_2(){
        LocalDate maxSaleDay = ecommerceService.getMaxSaleDay(LocalDate.of(2004,1, 1), LocalDate.now());
        assertEquals(LocalDate.of(2004,11,24),maxSaleDay);
    }

    @Test
    public void testMaxSaleDay_NoMaxSale(){
        assertThrows(DataNotFoundException.class, () -> ecommerceService.getMaxSaleDay(LocalDate.now(), LocalDate.now()));
    }
}
