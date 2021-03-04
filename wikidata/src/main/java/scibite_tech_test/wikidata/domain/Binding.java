package scibite_tech_test.wikidata.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

public @Data class Binding {
	
	@JacksonXmlProperty(localName = "name", isAttribute = true)
	private String name;
	@JacksonXmlProperty(localName = "literal")
	private String literal;
}
