package mk.ukim.finki.eshop.web;

import mk.ukim.finki.eshop.model.Category;
import mk.ukim.finki.eshop.model.Manufacturer;
import mk.ukim.finki.eshop.model.Product;
import mk.ukim.finki.eshop.service.CategoryService;
import mk.ukim.finki.eshop.service.ManufacturerService;
import mk.ukim.finki.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductController(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping({"/", "/products"})
    public String showProducts(@RequestParam(required = false) String searchText,
                               @RequestParam(required = false) Long category,
                               @RequestParam(required = false) Long manufacturer,
                               Model model) {
        List<Product> products;
        if (searchText == null && category == null && manufacturer == null) {
            products = this.productService.listAllProducts();
        }
        else {
            Category searchCategory = null;
            if (category != null) {
                searchCategory = this.categoryService.findById(category);
            }
            Manufacturer searchManufacturer = null;
            if (manufacturer != null) {
                searchManufacturer = this.manufacturerService.findById(manufacturer);
            }
            products = this.productService.filterProducts(searchText, searchCategory, searchManufacturer);
        }
        model.addAttribute("products", products);
        model.addAttribute("categories", this.categoryService.listAllCategories());
        model.addAttribute("manufacturers", this.manufacturerService.listAllManufacturers());
        return "home";
    }

    @GetMapping("/products/add")
    public String showAdd(Model model) {
        List<Category> categories = this.categoryService.listAllCategories();
        List<Manufacturer> manufacturers = this.manufacturerService.listAllManufacturers();
        model.addAttribute("categories", categories);
        model.addAttribute("manufacturers", manufacturers);
        return "form.html";
    }

    @GetMapping("/products/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Product product = this.productService.findById(id);
        List<Category> categories = this.categoryService.listAllCategories();
        List<Manufacturer> manufacturers = this.manufacturerService.listAllManufacturers();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("manufacturers", manufacturers);
        return "form.html";
    }

    @PostMapping("/products/")
    public String create(@RequestParam String name,
                         @RequestParam Double price,
                         @RequestParam Integer quantity,
                         @RequestParam Long categoryId,
                         @RequestParam Long manufacturerId) {
        this.productService.create(name, price, quantity, categoryId, manufacturerId);
        return "redirect:/products";
    }

    @PostMapping("/products/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam Double price,
                         @RequestParam Integer quantity,
                         @RequestParam Long categoryId,
                         @RequestParam Long manufacturerId) {
        this.productService.update(id, name, price, quantity, categoryId, manufacturerId);
        return "redirect:/products";
    }

    @PostMapping("/products/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.productService.delete(id);
        return "redirect:/products";
    }

}
