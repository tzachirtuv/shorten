package springboot.ebay.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ebay.exam.model.ShortenUrl;

public interface ShortenRepository extends JpaRepository<ShortenUrl, String> {
    
}