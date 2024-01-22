package mk.ukim.finki.wp.september2021.service.impl;

import mk.ukim.finki.wp.september2021.model.News;
import mk.ukim.finki.wp.september2021.model.NewsCategory;
import mk.ukim.finki.wp.september2021.model.NewsType;
import mk.ukim.finki.wp.september2021.model.exceptions.InvalidNewsCategoryIdException;
import mk.ukim.finki.wp.september2021.model.exceptions.InvalidNewsIdException;
import mk.ukim.finki.wp.september2021.repository.NewsCategoryRepository;
import mk.ukim.finki.wp.september2021.repository.NewsRepository;
import mk.ukim.finki.wp.september2021.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsCategoryRepository categoryRepository;

    public NewsServiceImpl(NewsRepository newsRepository, NewsCategoryRepository categoryRepository) {
        this.newsRepository = newsRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<News> listAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElseThrow(InvalidNewsIdException::new);
    }

    @Override
    public News create(String name, String description, Double price, NewsType type, Long category) {
        NewsCategory newsCategory = categoryRepository.findById(category).orElseThrow(InvalidNewsCategoryIdException::new);
        return newsRepository.save(new News(name, description, price, type, newsCategory));
    }

    @Override
    public News update(Long id, String name, String description, Double price, NewsType type, Long category) {
        NewsCategory newsCategory = categoryRepository.findById(category).orElseThrow(InvalidNewsCategoryIdException::new);
        News news = findById(id);
        news.setName(name);
        news.setDescription(description);
        news.setPrice(price);
        news.setType(type);
        news.setCategory(newsCategory);

        return newsRepository.save(news);
    }

    @Override
    public News delete(Long id) {
        News news = findById(id);
        newsRepository.delete(news);

        return news;
    }

    @Override
    public News like(Long id) {
        News news = findById(id);
        news.setLikes(news.getLikes() + 1);

        return newsRepository.save(news);
    }

    @Override
    public List<News> listNewsWithPriceLessThanAndType(Double price, NewsType type) {
        if (price != null && type != null){
            return newsRepository.findNewsByTypeAndPriceLessThan(type, price);
        } else if (price != null){
            return newsRepository.findNewsByPriceLessThan(price);
        } else if (type != null) {
            return newsRepository.findNewsByType(type);
        } else {
            return newsRepository.findAll();
        }
    }
}
