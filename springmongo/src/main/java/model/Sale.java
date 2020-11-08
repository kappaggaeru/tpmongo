package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "sales")
public class Sale {
    @Id
    private String id;
    private List<Product> products;
    private String delivery;

    public Sale(List<Product> products, String delivery) {
        this.products = products;
        this.delivery = delivery;
    }

    public double getTotal(){
        double res = 0.0;
        for (Product p: this.products) {
            res += p.getPrice();
        }
        return  res;
    }
    public String getId() {
        return id;
    }
    public List<Product> getProducts(){
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
    public String getDelivery(){
        return delivery;
    }
}