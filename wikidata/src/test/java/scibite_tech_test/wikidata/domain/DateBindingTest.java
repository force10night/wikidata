package scibite_tech_test.wikidata.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateBindingTest {
	
	DateBinding binding = new DateBinding();
	
	@BeforeEach
	void setUp() {
		binding.setLiteral("1966-10-09T00:00:00Z");
	}

	@Test
	void testGetDate() {
		LocalDateTime dateTime = binding.getDate();
		assertNotNull(dateTime);
		assertEquals(LocalDateTime.class,dateTime.getClass());
		assertEquals(1966,dateTime.getYear());
		assertEquals(10,dateTime.getMonthOfYear());
		assertEquals(9,dateTime.getDayOfMonth());
	}

}
