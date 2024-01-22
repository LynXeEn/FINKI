package mk.ukim.finki.wp.september2021.repository;

import mk.ukim.finki.wp.september2021.model.News;
import mk.ukim.finki.wp.september2021.model.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findNewsByTypeAndPriceLessThan(NewsType type, Double price);
    List<News> findNewsByType(NewsType type);
    List<News> findNewsByPriceLessThan(Double price);
}
