package dev.yuizho.soap_demo;

import dev.yuizho.soap_demo.inventory.InvestoryServiceImpl;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {
    @Bean
    public Endpoint endpoint(Bus bus, InvestoryServiceImpl investoryService) {
        EndpointImpl endpoint = new EndpointImpl(bus, investoryService);
        endpoint.publish("/investry"); // http://localhost:8080/services/investry で公開
        return endpoint;
    }
}
