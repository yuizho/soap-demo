package dev.yuizho.soap_demo.inventory;

import dev.yuizho.inventory.*;
import jakarta.jws.WebService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@WebService(
        serviceName = "InvestoryService",
        portName = "InventoryPortType"
)
public class InvestoryServiceImpl implements InventoryPortType {
    private final Map<String, Product> products = new ConcurrentHashMap<>();

    {
        var apple = new Product();
        apple.setId("1");
        apple.setName("Apple");
        apple.setPrice(100);
        apple.setQuantity(10);
        products.put(apple.getId(), apple);

        var banana = new Product();
        banana.setId("2");
        banana.setName("Banana");
        banana.setPrice(200);
        banana.setQuantity(20);
        products.put(banana.getId(), banana);
    }

    @Override
    public UpdateStockResponse updateStock(UpdateStockRequest parameters) {
        var id = parameters.getProductId();
        var response = new UpdateStockResponse();
        if (!products.containsKey(id)) {
            response.setSuccess(false);
            response.setMessage("Product not found");
        } else {
            response.setSuccess(true);
            var product = products.get(id);
            product.setQuantity(product.getQuantity() + parameters.getQuantity());
            response.setMessage("Updated!!");
        }
        return response;
    }

    @Override
    public GetProductResponse getProduct(GetProductRequest parameters) {
        var id = parameters.getProductId();
        if (!products.containsKey(id)) {
            return null;
        }

        var product = products.get(id);
        var response = new GetProductResponse();
        response.setProduct(product);
        return response;
    }
}
