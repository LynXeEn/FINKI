package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Category;
import mk.ukim.finki.wp.exam.example.model.Product;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidProductIdException;
import mk.ukim.finki.wp.exam.example.repository.CategoryRepository;
import mk.ukim.finki.wp.exam.example.repository.ProductRepository;
import mk.ukim.finki.wp.exam.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(InvalidProductIdException::new);
    }

    @Override
    public Product create(String name, Double price, Integer quantity, List<Long> categories) {
        List<Category> categories1 = categoryRepository.findAllById(categories);
        return productRepository.save(new Product(name, price, quantity, categories1));
    }

    @Override
    public Product update(Long id, String name, Double price, Integer quantity, List<Long> categories) {
        Product product = findById(id);
        List<Category> categories1 = categoryRepository.findAllById(categories);
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategories(categories1);

        return productRepository.save(product);
    }

    @Override
    public Product delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);

        return product;
    }

    @Override
    public List<Product> listProductsByNameAndCategory(String name, Long categoryId) {
        String nameLikePattern = "%" + name + "%";
        // ime na produkt, kategorija
        if (name != null && categoryId != null){
            Category category = categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new);
            return productRepository.findProductsByNameLikeAndCategoriesContaining(nameLikePattern, category);
        } else if (name != null) {
            return productRepository.findProductsByNameLike(nameLikePattern);
        } else if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new);
            return productRepository.findProductsByCategoriesContaining(category);
        } else {
            return productRepository.findAll();
        }
    }
}
