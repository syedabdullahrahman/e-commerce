package syed.abdullah.demo;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import syed.abdullah.demo.entity.Wishlist;
import syed.abdullah.demo.repository.WishlistRepository;
import syed.abdullah.demo.service.EcommerceService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class EcommerceUnitTests {

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private EcommerceService ecommerceService;

    @Test
    public void testGetWishlistByCustomerId() {
        Mockito.when(wishlistRepository.findAllByCustomerId(103)).thenReturn(Lists.emptyList());
        List<Wishlist> wishlist = ecommerceService.getWishlistByCustomerId(103);
        assertEquals(0, wishlist.size());
    }

}
