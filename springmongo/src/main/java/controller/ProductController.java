package controller;

import model.Product;
import org.springframework.web.bind.annotation.*;
import repository.ProductRepository;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/products")
public class ProductController {
    public final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    @GetMapping("/product/{id}")
    public Optional<Product> getProduct(@PathVariable String id){
        return productRepository.findById(id);
    }
    @PostMapping("/create")
    public String createProduct(@RequestBody Product p){
        Product productCreated = new Product(p.getName(), p.getDescription(), p.getPrice(),p.getStock());
        productRepository.save(productCreated);
        return "product created " + productCreated.getName();
    }
    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable String id, @RequestBody Product newProduct){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setName(newProduct.getName());
            product.setDescription(newProduct.getDescription());
            product.setStock(newProduct.getStock());
            product.setPrice(newProduct.getPrice());
            productRepository.save(product);
            return "product updated " + product.getName();
        }else{
            return "product was not found";
        }
    }
    @DeleteMapping(value = "/delete/{id}", produces = "application/json; charset:utf-8")
    public String deleteProduct(@PathVariable String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return "product deleted";
        }else{
            return "product was not found";
        }
    }
}
