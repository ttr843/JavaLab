package payload;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Product;

public class AddProductPayload implements PayLoad {
    private Product product;
    private String token;
    public String command = "add product";
    public String getToken() {
        return token;
    }

    public AddProductPayload(Product product, String token) {
        this.product = product;
        this.token = token;
    }

    public AddProductPayload(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String convertToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
