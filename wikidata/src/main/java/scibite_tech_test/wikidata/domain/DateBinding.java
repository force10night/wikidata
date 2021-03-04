package scibite_tech_test.wikidata.domain;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
public @Data class DateBinding extends Binding {	
	@JacksonXmlProperty(localName = "datatype", isAttribute = true)
	private String datatype;
	
	public LocalDateTime getDate() {
		DateTimeFormatter dtf = DateTimeFormat.forPattern("YYYY-MM-dd'T'HH:mm:ss'Z'");
		return LocalDateTime.parse(getLiteral(), dtf);
	}
}
