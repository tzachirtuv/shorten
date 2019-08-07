package springboot.ebay.exam;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import springboot.ebay.exam.model.UrlDto;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port ;

    @Autowired
    private TestRestTemplate restTemplate;

    String encodePath = "api/v1/shorten";
    String getPath = "api/v1/go";

    @Test
    public void getRedirectViaShorten() throws Exception {
        String path ="http://localhost:" + port + "/" + getPath + "/17kZ6Z";
        Object res = this.restTemplate.getForEntity(path,Object.class);
        assertThat(res.toString().contains("https://www.google.com/"));
    }


    @Test
    public void getShortenUrl() throws Exception {
        UrlDto dto = new UrlDto("https://www.google.co.il/", null);
        HttpEntity<UrlDto> request = new HttpEntity<UrlDto>(dto);
        String path ="http://localhost:" + port + "/" + encodePath;
        ResponseEntity<UrlDto> res = this.restTemplate.postForEntity(path,request,UrlDto.class);
        assertThat(res.getBody().getUrl().length() == 6);
    }


}