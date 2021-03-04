package scibite_tech_test.wikidata.transformer;

import static org.junit.jupiter.api.Assertions.*;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

class DobToAgeInYrsTest {

	@Test
	void testApply() {
		DateTimeFormatter dtf = DateTimeFormat.forPattern("YYYY-MM-dd'T'HH:mm:ss'Z'");
		LocalDateTime ldt = LocalDateTime.parse("1966-10-09T00:00:00Z", dtf);
		assertNotNull(ldt.getYear());
		DobToAgeInYrs ageCalculator = new DobToAgeInYrs();
		assertEquals(54, ageCalculator.apply(ldt));
	}

}
