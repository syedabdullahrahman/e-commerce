package syed.abdullah.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import syed.abdullah.demo.entity.Customer;
import syed.abdullah.demo.entity.Wishlist;
import syed.abdullah.demo.repository.CustomerRepository;
import syed.abdullah.demo.repository.WishlistRepository;
import syed.abdullah.demo.service.EcommerceService;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class EcommerceUnitTests {

    @Mock
    private WishlistRepository wishlistRepository;
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private EcommerceService ecommerceService;

    @Test
    public void testGetWishlistByCustomerId() {
        Customer customerEmptyWishList = Customer.builder().wishlists(Set.of()).build();
        Mockito.when(customerRepository.findById(103)).thenReturn(Optional.ofNullable(customerEmptyWishList));
        Set<Wishlist> wishlist = ecommerceService.getWishlistByCustomerId(103);
        assertEquals(0, wishlist.size());
    }

}
