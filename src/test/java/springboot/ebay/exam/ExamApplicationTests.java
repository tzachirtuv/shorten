package springboot.ebay.exam;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import springboot.ebay.exam.model.UrlDto;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamApplicationTests {

	@Autowired
	ShortenController controller;

	@Test
	public void baseTest() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void encodeTest() throws Exception {
		int expectedLength = 6;
		UrlDto url = new UrlDto("https://www.test.com/",null); 
		UrlDto response = controller.encodeController(url);
		assertEquals(expectedLength, response.getUrl().length());
		
	}

	@Test(expected = Exception.class)
	public void encodeTest_InvalidUrl() throws Exception {
		UrlDto url = new UrlDto("invalidUrl",null); 
		controller.encodeController(url);
	}

	@Test
	public void redirectTest_ValidShortern() throws Exception {
		String shorten = "17kZ6Z"; //predefiend in DB
		ModelAndView model = controller.get(shorten);
		HttpStatus status = model.getStatus();
		assertEquals(HttpStatus.OK, status);
	}

	@Test
	public void redirectTest_InvalidShortern() throws Exception {
		String shorten = "Dummy";
		ModelAndView model = controller.get(shorten);
		HttpStatus status = model.getStatus();
		assertEquals(HttpStatus.NOT_FOUND, status);
	}



}
