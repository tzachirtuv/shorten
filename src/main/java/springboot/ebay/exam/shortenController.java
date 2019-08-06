package springboot.ebay.exam;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springboot.ebay.exam.model.shortenUrl;
import springboot.ebay.exam.service.ShortenService;



@RequestMapping("/api/v1")
@RestController
public class shortenController{

    @Autowired
    private ShortenService shortenService;

    @RequestMapping(value = "/shorten", method = RequestMethod.POST)
    shortenUrl creatShortenUrl() {
      Optional<shortenUrl> tt = shortenService.findById("Xfh3fs0tHj4");
        return tt.get();
      //  return "SUCCESS";
    }
}