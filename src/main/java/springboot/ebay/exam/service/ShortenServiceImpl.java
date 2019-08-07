package springboot.ebay.exam.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import springboot.ebay.exam.converter.UrlConverter;
import springboot.ebay.exam.model.SchemaEntity;
import springboot.ebay.exam.model.UrlDto;
import springboot.ebay.exam.repository.ShortenRepository;
import springboot.ebay.exam.util.LRUCache;

import org.springframework.stereotype.Service;

@Service
public class ShortenServiceImpl implements ShortenService {

    private String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Autowired
    private ShortenRepository shortenRepository;

    @Autowired(required = false)
    LRUCache lru;

    public ShortenServiceImpl() {
        lru = new LRUCache(2);
    }

    @Override
    public UrlDto createShortenUrl(String longUrl) {
        String shorten = encode();

        SchemaEntity newData = new SchemaEntity();
        newData.setId(shorten);
        newData.setLongUrl(longUrl);
        shortenRepository.save(newData);
        lru.put(shorten, longUrl);
        UrlDto data = UrlConverter.entityToDto(newData);
       
        return data;
    }

    @Override
    public UrlDto GetLongUrl(String shortenUrl) {
        UrlDto result = new UrlDto();
        String urlInCache = lru.get(shortenUrl);
        if (urlInCache == null) {
            Optional<SchemaEntity> data = shortenRepository.findById(shortenUrl);

            data.ifPresent(d -> {
                lru.put(shortenUrl, d.getLongUrl());
                result.setUrl(d.getLongUrl());

            });
        }else{
            result.setUrl(urlInCache);
        }
        return result;
    }

    private String encode() {
        String shorten = generateShorten();
        Optional<SchemaEntity> current = shortenRepository.findById(shorten);
        while (current.isPresent()) {
            shorten = generateShorten();
            current = shortenRepository.findById(shorten);
            ;
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