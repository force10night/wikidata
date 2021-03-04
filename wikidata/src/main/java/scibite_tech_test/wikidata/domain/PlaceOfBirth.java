package scibite_tech_test.wikidata.domain;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper=true)
public @Data class PlaceOfBirth extends WikiDataSparql {
	private List<Result<PlaceBinding>> results; 
}
