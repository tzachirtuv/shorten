package springboot.ebay.exam.service;

import springboot.ebay.exam.model.UrlData;

public interface ShortenService {
    
    UrlData createShortenUrl(String longUrl);
    UrlData GetLongUrl(String shortenUrl); 
    
}