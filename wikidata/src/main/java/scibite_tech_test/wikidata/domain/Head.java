package scibite_tech_test.wikidata.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

/**
 * 
 * @author force
 * WikiData SparQL POJO for header element
 * 
 * <head><variable name='a'/></head>
 *
 */

public @Data class Head {
	@JacksonXmlProperty(localName = "variable")
	private Variable variable;
}
