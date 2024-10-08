package syed.abdullah.demo;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;

/**
 * Updated by Abdullah
 * Date: 08-Oct-24
 * Time: 6:14 PM
 */
@TestConfiguration(proxyBeanMethods = false)
@ActiveProfiles("test")
public class ContainersConfig {

    @Bean
    @ServiceConnection
    @RestartScope
    MySQLContainer<?> mysqlSQLContainer(){
        return new MySQLContainer<>("mysql:latest");
    }

}
