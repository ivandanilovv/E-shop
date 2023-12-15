package mk.ukim.finki.eshop.service;

import mk.ukim.finki.eshop.model.Category;
import mk.ukim.finki.eshop.model.Manufacturer;
import mk.ukim.finki.eshop.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAllProducts();

    Product findById(Long id);

    Product create(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId);

    Product update(Long id, String name, Double price, Integer quantity, Long categoryId, Long manufacturerId);

    Product delete(Long id);

}
