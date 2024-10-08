package syed.abdullah.demo;

import org.springframework.boot.SpringApplication;

/**
 * Purpose: To run the application without explicitly creating Database containers or installing database


 * Updated by Abdullah
 * Date: 08-Oct-24
 * Time: 6:15 PM
 */

public class DemoApplicationTest {
    public static void main(String[] args) {
        SpringApplication
                .from(DemoApplication::main)
                .with(ContainersConfig.class)
                .run(args);
    }
}
