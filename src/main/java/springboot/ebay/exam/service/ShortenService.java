package springboot.ebay.exam.service;

import springboot.ebay.exam.model.UrlDto;

public interface ShortenService {
    
    UrlDto createShortenUrl(String longUrl);
    UrlDto GetLongUrl(String shortenUrl); 
    
}