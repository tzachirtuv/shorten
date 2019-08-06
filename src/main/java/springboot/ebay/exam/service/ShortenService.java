package springboot.ebay.exam.service;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ebay.exam.model.shortenUrl;

public interface ShortenService extends JpaRepository<shortenUrl, String>{
    
}