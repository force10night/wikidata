package scibite_tech_test.wikidata.domain;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
public @Data class DateOfBirth extends WikiDataSparql {
	private List<Result<DateBinding>> results;
}
