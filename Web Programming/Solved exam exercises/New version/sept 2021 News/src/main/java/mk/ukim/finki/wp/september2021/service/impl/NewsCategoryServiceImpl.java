package mk.ukim.finki.wp.september2021.service.impl;

import mk.ukim.finki.wp.september2021.model.NewsCategory;
import mk.ukim.finki.wp.september2021.model.exceptions.InvalidNewsCategoryIdException;
import mk.ukim.finki.wp.september2021.repository.NewsCategoryRepository;
import mk.ukim.finki.wp.september2021.service.NewsCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsCategoryServiceImpl implements NewsCategoryService {

    private final NewsCategoryRepository categoryRepository;

    public NewsCategoryServiceImpl(NewsCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public NewsCategory findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(InvalidNewsCategoryIdException::new);
    }

    @Override
    public List<NewsCategory> listAll() {
        return categoryRepository.findAll();
    }

    @Override
    public NewsCategory create(String name) {
        return categoryRepository.save(new NewsCategory(name));
    }
}
