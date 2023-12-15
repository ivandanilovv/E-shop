package mk.ukim.finki.eshop.service.impl;

import mk.ukim.finki.eshop.model.Category;
import mk.ukim.finki.eshop.model.Manufacturer;
import mk.ukim.finki.eshop.model.Product;
import mk.ukim.finki.eshop.repository.CategoryRepository;
import mk.ukim.finki.eshop.repository.ManufacturerRepository;
import mk.ukim.finki.eshop.repository.ProductRepository;
import mk.ukim.finki.eshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Product> listAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public Product create(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId).orElse(null);
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId).orElse(null);
        Product product = new Product(name, price, quantity, category, manufacturer);
        return this.productRepository.save(product);
    }

    @Override
    public Product update(Long id, String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId).orElse(null);
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId).orElse(null);
        Product product = this.productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setCategory(category);
            product.setManufacturer(manufacturer);
        }
        return this.productRepository.save(product);
    }

    @Override
    public Product delete(Long id) {
        Product product = this.productRepository.findById(id).orElse(null);
        this.productRepository.delete(product);
        return product;
    }
}
