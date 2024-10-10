package syed.abdullah.demo.config.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * Updated by Abdullah
 * Date: 10-Oct-24
 * Time: 2:27 PM
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Simple E-commerce API",
                version = "1.0.0",
                description = "A REST API for managing an e-commerce platform."
        ),
        servers = {
                @Server(url = "http://localhost:9081/api", description = "Local server")
        }
)
public class OpenApiConfig {
    // Additional configuration can be added here if needed
}