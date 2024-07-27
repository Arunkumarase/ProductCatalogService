package stubs;

import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Primary
@Service
public class ProductServiceStub implements IProductService {

    Map<Long, Product> productMap;

    public ProductServiceStub(){
        productMap = new HashMap<>();
    }
    @Override
    public List<Product> getAllProducts() {
        return (List)productMap.values();
    }

    @Override
    public Product getProduct(Long productId) {
        return productMap.get(productId);
    }

    @Override
    public Product createProduct(Product product) {
        return productMap.put(product.getId(),product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        productMap.put(id,product);
        return productMap.get(id);

    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }
}
