package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Product;

import java.util.List;

public class ProductsPayload implements PayLoad{

    public ProductsPayload() {
    }
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public ProductsPayload(List<Product> products) {
        this.products = products;
    }

    @Override
    public String convertToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
