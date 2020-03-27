package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Product;

public class BuyProductPayload implements PayLoad{

    private Product product;
    public BuyProductPayload(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
    public BuyProductPayload() {
    }

    @Override
    public String convertToJson() throws JsonProcessingException {
        return null;
    }
}
