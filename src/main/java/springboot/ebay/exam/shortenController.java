package springboot.ebay.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import springboot.ebay.exam.service.ShortenService;
import springboot.ebay.exam.model.UrlDto;
import java.net.URL;

@RequestMapping("/api/v1")
@RestController
public class ShortenController {

   @Autowired
   private ShortenService shortenService;

   @RequestMapping(value = "/shorten", method = RequestMethod.POST)
   public UrlDto encodeController(@RequestBody UrlDto url) throws Exception {
      String longUrl = url.getUrl();
      try {
         new URL(longUrl).toURI();

         UrlDto response = shortenService.createShortenUrl(url.getUrl());

         return response;
      } catch (Exception e) {
         throw new Exception("Invalid url", e);
      }

   }

   @RequestMapping(value = "/go/{id}", method = RequestMethod.GET)
   public ModelAndView get(@PathVariable("id") String id) throws Exception {
      try {
         UrlDto fullUrl = shortenService.GetLongUrl(id);
         if (fullUrl.getUrl() == null) {

            ModelMap modelMap = new ModelMap();
            modelMap.put("id", id);
            return new ModelAndView("notFound", modelMap, HttpStatus.NOT_FOUND);
         } else {
            String longUrl = fullUrl.getUrl();
            ModelAndView model = new ModelAndView(new RedirectView(longUrl, true));
            model.setStatus(HttpStatus.OK);
            return model;
         }
      } catch (Exception e) {
         throw new Exception("Error while tryinh to redirect to with id: " + id, e);
      }

   }
}