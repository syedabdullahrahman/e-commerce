package syed.abdullah.demo;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import syed.abdullah.demo.entity.Wishlist;
import syed.abdullah.demo.repository.WishlistRepository;
import syed.abdullah.demo.service.EcommerceService;

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
}
