package mk.ukim.finki.eshop.service;

import mk.ukim.finki.eshop.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> listAllCategories();

    Category findById(Long id);


}
