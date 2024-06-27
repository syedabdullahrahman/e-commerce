package syed.abdullah.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import syed.abdullah.demo.entity.Wishlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class EcommerceControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetWishList() {
        ResponseEntity<Wishlist[]> response = restTemplate.getForEntity("/api/wishlist/103", Wishlist[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(7, response.getBody().length);
    }
}
