package product;

import java.util.List;

public class Product {
    private static List<Product> products;
    private final String name;
    private final long price;

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    private Product(String name, long price) {
        this.name = name;
        this.price = price;
    }
    public static List<Product> getProducts(){
        if(products == null){
            products = List.of(
                new Product("Coke",10000),
                new Product("Pepsi",10000),
                new Product("Soda",20000));
        }
        return products;
    }
}
