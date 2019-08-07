package springboot.ebay.exam.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import springboot.ebay.exam.model.ShortenUrl;
import springboot.ebay.exam.model.UrlData;
import springboot.ebay.exam.repository.ShortenRepository;
import org.springframework.stereotype.Service;

@Service
public class ShortenServiceImpl implements ShortenService{

    String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
     

    @Autowired
    ShortenRepository shortenRepository;

    @Override
    public UrlData createShortenUrl(String longUrl) {
        String shorten = encode();
        ShortenUrl newData = new ShortenUrl();
        newData.setId(shorten);
        newData.setLongUrl(longUrl);
        shortenRepository.save(newData);
        UrlData data = new UrlData();
        data.setId(shorten);
        
        return data;
    }

    @Override
    public UrlData GetLongUrl(String shortenUrl) {
        Optional<ShortenUrl> data = shortenRepository.findById(shortenUrl);
        UrlData res = new UrlData();
        data.ifPresent( d -> {
             res.setUrl(d.getLongUrl());
            
        });
        return res;
    }

    private String encode() {
        String shorten = generateShorten();
        Optional<ShortenUrl> current = shortenRepository.findById(shorten);
         while (current.isPresent()) {
             shorten = generateShorten();
             current = shortenRepository.findById(shorten);;
         }
        return shorten;
    }


    private String generateShorten() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(alphabet.charAt(rand.nextInt(62)));
        }
        return sb.toString();
    }    
}