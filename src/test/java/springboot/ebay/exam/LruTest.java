package springboot.ebay.exam;

import static org.junit.Assert.assertEquals;


import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import springboot.ebay.exam.util.LRUCache;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LruTest {

    @LocalServerPort
    private int port ;


    @Test
    public void baseTest() {
        LRUCache cache = new LRUCache(2);
        String url = "https://www.google.com/";
        String shorten = "yOp9Ui";
        cache.put(shorten, url);
        String result = cache.get(shorten);
        assertEquals(url, result);
    }

    @Test
    public void keyNotExistTest() {
        LRUCache cache = new LRUCache(2);
        String shorten = "yOp9Ui";
        String result = cache.get(shorten);
        assertEquals(null, result);
    }

    @Test
    public void cacheIsFull() {
        LRUCache cache = new LRUCache(1);
        String url = "https://www.google.com/";
        String shorten = "firstKey";
        cache.put(shorten, url);

        url = "https://www.test.co.il/";
        shorten = "newKey";
        cache.put(shorten, url);
        String result = cache.get(shorten);
        assertEquals(url, result);
        shorten = "firstKey";
        result = cache.get(shorten);
        assertEquals(null, result);
    }

}
