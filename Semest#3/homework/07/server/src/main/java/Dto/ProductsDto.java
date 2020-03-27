package Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Product;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto implements ForOneDto {
    List<Product> products;
    public static ProductsDto from(List<Product> products) {
        return new ProductsDto(products);
    }
}
