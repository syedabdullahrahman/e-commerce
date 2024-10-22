package syed.abdullah.demo.postgres.integration;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("postgresdb")
public abstract class AbstractIntegrationTestPostgresDB {

    //@Container
    @ServiceConnection
    static PostgreSQLContainer<?> pgvectorDb = new PostgreSQLContainer<>("pgvector/pgvector:pg16");

    @BeforeAll
    static void beforeAll() {
        pgvectorDb.start();
    }
}