package mk.ukim.finki.eshop.repository;

import mk.ukim.finki.eshop.model.Category;
import mk.ukim.finki.eshop.model.Manufacturer;
import mk.ukim.finki.eshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findProductsByNameContainingAndCategoryAndManufacturer(String text, Category category, Manufacturer manufacturer);

    List<Product> findProductsByNameContainingAndCategory(String text, Category category);

    List<Product> findProductsByNameContainingAndManufacturer(String text, Manufacturer manufacturer);

    List<Product> findProductsByCategoryAndManufacturer(Category category, Manufacturer manufacturer);

    List<Product> findProductsByNameContaining(String text);

}
