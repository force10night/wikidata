package scibite_tech_test.wikidata.xml;

import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import scibite_tech_test.wikidata.domain.DateBinding;
import scibite_tech_test.wikidata.domain.DateOfBirth;
import scibite_tech_test.wikidata.domain.PlaceBinding;
import scibite_tech_test.wikidata.domain.PlaceOfBirth;


class DeserialiserTest {
	
	private XmlMapper xmlMapper = new XmlMapper();
	private static final String davidCameronPob = "<?xml version='1.0' encoding='UTF-8'?>\n"
			+ "<sparql xmlns='http://www.w3.org/2005/sparql-results#'>\n"
			+ "	<head>\n"
			+ "		<variable name='pob'/>\n"
			+ "	</head>\n"
			+ "	<results>\n"
			+ "		<result>\n"
			+ "			<binding name='pob'>\n"
			+ "				<literal xml:lang='en'>Greater London</literal>\n"
			+ "			</binding>\n"
			+ "		</result>\n"
			+ "	</results>\n"
			+ "</sparql>";
	private static final String davidCamerondob = "<?xml version='1.0' encoding='UTF-8'?>\n"
			+ "<sparql xmlns='http://www.w3.org/2005/sparql-results#'>\n"
			+ "	<head>\n"
			+ "		<variable name='dob'/>\n"
			+ "	</head>\n"
			+ "	<results>\n"
			+ "		<result>\n"
			+ "			<binding name='dob'>\n"
			+ "				<literal datatype='http://www.w3.org/2001/XMLSchema#dateTime'>1966-10-09T00:00:00Z</literal>\n"
			+ "			</binding>\n"
			+ "		</result>\n"
			+ "	</results>\n"
			+ "</sparql>";

	@Test
	void testDavidCameronPob() {
		PlaceOfBirth xml = null;
		try {
		xml =  xmlMapper.readValue(davidCameronPob,PlaceOfBirth.class);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		assertNotNull(xml);
		assertNotNull(xml.getHead());
		assertNotNull(xml.getResults());
		assertEquals(1,xml.getResults().size());
		assertNotNull(xml.getResults().get(0));
		assertNotNull(xml.getResults().get(0).getBinding());
		PlaceBinding binding = xml.getResults().get(0).getBinding();
		assertNotNull(binding);
    	assertEquals("pob",binding.getName());
		assertNotNull(binding.getLiteral());
		assertEquals("Greater London",binding.getLiteral());
	}

	

	@Test
	void testDavidCamerondDob() {
		DateOfBirth xml = null;
		try {
		xml = xmlMapper.readValue(davidCamerondob,DateOfBirth.class);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		assertNotNull(xml);
		assertNotNull(xml.getHead());
		assertNotNull(xml.getResults());
		assertEquals(1,xml.getResults().size());
		assertNotNull(xml.getResults().get(0).getBinding());
		DateBinding binding = xml.getResults().get(0).getBinding();
		assertNotNull(binding);
		assertEquals("dob",binding.getName());
		assertNotNull(binding.getLiteral());
		assertEquals("1966-10-09T00:00:00Z",binding.getLiteral());
		assertNotNull(binding.getDate());
		assertEquals(1966,binding.getDate().getYear());
	}
	
	
		
}


