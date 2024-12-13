package bdb.Backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Consulta Clientes - Banco de Bogotá")
                        .version("1.0")
                        .description("""
                            API REST para consulta de clientes - Prueba Técnica Banco de Bogotá Colombia
                            
                            Características:
                            * Consulta de clientes por tipo y número de documento
                            * Validación de datos
                            * Manejo de respuestas estandarizadas
                            
                            Servidor de desarrollo: http://localhost:8090
                            """)
                        .contact(new Contact()
                                .name("Anderson Arévalo Madrid")
                                .email("arevalomadrid62776@gmail.com")
                                .url("www.linkedin.com/in/andersons-arevalo-madrid-516bb7183")));
    }

}
