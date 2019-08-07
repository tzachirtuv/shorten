package springboot.ebay.exam;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WebController{

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request) {
        return new ModelAndView("index");
    }
}