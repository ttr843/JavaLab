package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Product;

public class DeleteProductPayload implements PayLoad{
    public DeleteProductPayload() {
    }
    private Product product;

    public Product getProduct() {
        return product;
    }

    public DeleteProductPayload(Product product) {
        this.product = product;
    }

    @Override
    public String convertToJson() throws JsonProcessingException {
        return null;
    }
}
