package scibite_tech_test.wikidata.domain;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Andrew Nightingale
 * WikiData SparQL Binding POJO
 */
@EqualsAndHashCode(callSuper=true)
public @Data class PlaceBinding extends Binding  {
	
	@JacksonXmlProperty(localName = "lang", isAttribute = true)
	private String lang;
	

}
