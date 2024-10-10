package syed.abdullah.demo.web;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import syed.abdullah.demo.AbstractControllerTest;
import syed.abdullah.demo.AbstractIntegrationTest;
import syed.abdullah.demo.dto.MaxSaleDate;
import syed.abdullah.demo.dto.SaleAmount;
import syed.abdullah.demo.entity.Wishlist;
import syed.abdullah.demo.exception.ErrorDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EcommerceControllerIntegrationTest extends AbstractControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetWishList() {
        ResponseEntity<Wishlist[]> response = restTemplate.getForEntity("/api/wishlist/103", Wishlist[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(7, Objects.requireNonNull(response.getBody()).length);
    }

    @Test
    void testGetWishList2(){
        Response response = RestAssured
                .given()
                .when()
                .get("/api/wishlist/103")
                .then()
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());

        Wishlist[] wishlist = response.as(Wishlist[].class);
        assertEquals(7, wishlist.length);
    }

    @Test
    public void testGetWishlistByInvalidCustomerId() {
        ResponseEntity<ErrorDetails> response = restTemplate.getForEntity("/api/wishlist/999999999", ErrorDetails.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Customer not found by ID: 999999999", response.getBody().getMessage());
    }

    @Test
    public void testTotalSalesToday() {
        ResponseEntity<SaleAmount> response = restTemplate.getForEntity("/api/sales/today", SaleAmount.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        BigDecimal totalSalesToday = Objects.requireNonNull(response.getBody()).amount();
        assertEquals(BigDecimal.valueOf(0), totalSalesToday);
    }

    @Test
    public void testMaxSaleDay() {
        Map<String, String> params = new HashMap<>();
        params.put("startDate", "01-Jan-2004");
        params.put("endDate", "31-Dec-2004");
        ResponseEntity<MaxSaleDate> response = restTemplate.getForEntity("/api/sales/max-day?startDate={startDate}&endDate={endDate}", MaxSaleDate.class, params);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        LocalDate maxSaleDay = response.getBody().date();
        assertEquals(LocalDate.of(2004, 11, 24), maxSaleDay);
    }

    @Test
    public void testMaxSaleDayNoMaxSaleFound() {
        Map<String, String> params = new HashMap<>();
        params.put("startDate", "01-Jan-2023");
        params.put("endDate", "31-Dec-2023");
        ResponseEntity<ErrorDetails> response = restTemplate.getForEntity("/api/sales/max-day?startDate={startDate}&endDate={endDate}", ErrorDetails.class, params);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No date found between 2023-01-01 and 2023-12-31", response.getBody().getMessage());
    }
}
