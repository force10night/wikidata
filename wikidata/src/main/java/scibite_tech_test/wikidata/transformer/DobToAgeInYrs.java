package scibite_tech_test.wikidata.transformer;

import org.joda.time.LocalDateTime;
import org.joda.time.Years;

import java.util.function.Function;

/**
 * @author Andrew Nightingale
 * Function class that calculates the number of whole years between todays date and a provided 
 * date of birth.
 *
 */
public final class DobToAgeInYrs implements Function<LocalDateTime,Integer> {

	/**
	 * Calculates the number of whole years between todays date and a provided 
	 * date of birth.
	 * @return Of the absolute years between todays date and provided date of birth
	 * @param A date of birth in YYYY-MM-DD format.
	 */
	@Override
	public Integer apply(LocalDateTime dob) {
		LocalDateTime today = LocalDateTime.now();
		return Integer.valueOf( Years.yearsBetween(dob,today).getYears());
	}
}
