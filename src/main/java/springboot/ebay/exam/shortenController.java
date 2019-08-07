package springboot.ebay.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springboot.ebay.exam.service.ShortenService;
import springboot.ebay.exam.model.UrlData;


@RequestMapping("/api/v1")
@RestController
public class ShortenController {

   @Autowired
   ShortenService shortenService;

   @RequestMapping(value = "/shorten", method = RequestMethod.POST)
   public UrlData encodeController(@RequestBody UrlData url) {
      UrlData response = shortenService.createShortenUrl(url.getUrl());

      return response;
   }


   @RequestMapping(value = "/go/{id}", method = RequestMethod.GET)
   public UrlData decodeController(@PathVariable String id) {
      UrlData response = shortenService.GetLongUrl(id);
      
      return response;
   }
}