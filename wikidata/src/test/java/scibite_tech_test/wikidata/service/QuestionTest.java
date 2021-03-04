package scibite_tech_test.wikidata.service;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import scibite_tech_test.wikidata.domain.PlaceOfBirth;
import scibite_tech_test.wikidata.exceptions.RestfulRuntimeException;


//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
class QuestionTest {
	private Question service = new Question();
	
	@Test
	public void test_me() throws RestfulRuntimeException{
		assertEquals(54,Integer.valueOf(Question.ask("How old is David Cameron")));
		assertEquals(67,Integer.valueOf(Question.ask("How old is Tony Blair")));
		assertEquals("Edinburgh",
				Question.ask("What is the birth place name of Tony Blair ?")
		);
	}
	
	
	@Test
	void testAskAge() {
		Integer result = service.askAge("How old is David Cameron");
		assertNotNull(result);
		assertEquals(54,result);
	}

	@Test
	void testAskPlaceOfBirth() {
		String result = service.askPlaceOfBirth("What is the birth place name of Tony Blair ?");
		assertNotNull(result);
		assertEquals("Edinburgh",result);
	}
	
	@Test
	void testRestTemplate() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "https://query.wikidata.org/sparql?query=" 
	    + "select%20?res%20where%20%7B%20?z%20rdfs:label%20%22David%20Cameron%22"
	    + "@en%20.%20"
	    + "?z%20wdt:P19%20?a%20.%20" 
	    + "?a%20wdt:P1448%20?res%20%7D%20limit%201";
	 
	    URI uri = new URI(baseUrl);
	 
	    ResponseEntity<PlaceOfBirth> result = restTemplate.getForEntity(uri, PlaceOfBirth.class);
	    assertNotNull(result);
	    String placeOfBirth = result.getBody().getResults().get(0).getBinding().getLiteral();
	    assertEquals("Greater London",placeOfBirth);
	}

}
