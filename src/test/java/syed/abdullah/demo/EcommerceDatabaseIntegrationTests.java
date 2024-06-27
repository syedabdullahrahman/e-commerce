package syed.abdullah.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import syed.abdullah.demo.entity.Wishlist;
import syed.abdullah.demo.repository.WishlistRepository;
import syed.abdullah.demo.service.EcommerceService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testGetWishlistByCustomerId() {
        List<Wishlist> wishlist = ecommerceService.getWishlistByCustomerId(103);
        assertEquals(7, wishlist.size());
    }
}
