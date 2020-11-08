package controller;

import model.Sale;
import org.springframework.web.bind.annotation.*;
import repository.SaleRepository;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/sales")
public class SaleController {
    public final SaleRepository saleRepository;

    public SaleController(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @GetMapping("/all")
    public List<Sale> getSales(){
        return saleRepository.findAll();
    }
    @GetMapping("/sale/{id}")
    public Optional<Sale> getSale(@PathVariable String id){
        return saleRepository.findById(id);
    }
    @PostMapping("/create")
    public String addSale(@RequestBody Sale newSale){
        Sale sale = new Sale(newSale.getProducts(), newSale.getDelivery());
        saleRepository.save(sale);
        return "sale created";
    }
    @PutMapping("/update/{id}")
    public String updateSale(@PathVariable String id, @RequestBody Sale newSale){
        Optional<Sale> optionalSale = saleRepository.findById(id);
        if(optionalSale.isPresent()){
            Sale sale = optionalSale.get();
            sale.setProducts(newSale.getProducts());
            sale.setDelivery(newSale.getDelivery());
            saleRepository.save(sale);
            return "sale updated";
        }else{
            return "sale was not found";
        }
    }
    @DeleteMapping("/delete/{id}")
    public String deleteSale(@PathVariable String id){
        Optional<Sale> optionalSale = saleRepository.findById(id);
        if (optionalSale.isPresent()){
            saleRepository.deleteById(id);
            return "sale deleted";
        }else{
            return "sale was not found";
        }
    }
}