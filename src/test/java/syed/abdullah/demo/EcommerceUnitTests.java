package syed.abdullah.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import syed.abdullah.demo.entity.Customer;
import syed.abdullah.demo.entity.Wishlist;
import syed.abdullah.demo.repository.CustomerRepository;
import syed.abdullah.demo.service.EcommerceService;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EcommerceUnitTests {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private EcommerceService ecommerceService;

    @Test
    public void testGetWishlistByCustomerId() {
        Integer customerId = 103;
        Customer customerEmptyWishList = Customer.builder().id(customerId).wishlists(Set.of()).build();
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.ofNullable(customerEmptyWishList));
        Set<Wishlist> wishlist = ecommerceService.getWishlistByCustomerId(customerId);
        assertEquals(0, wishlist.size());
    }

}
