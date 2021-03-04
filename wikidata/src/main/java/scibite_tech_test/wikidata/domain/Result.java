package scibite_tech_test.wikidata.domain;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author Andrew Nightingale
 * WikiData SparQLResult POJO for XML element
 * 
 * <result><binding name='a'>
		<literal datatype='http://www.w3.org/2001/XMLSchema#dateTime'>1953-05-06T00:00:00Z</literal>
		</binding>
   </result>
 */

import lombok.Data;

public @Data class Result<T> {
	@JacksonXmlProperty(localName = "binding")
	private T binding;
}
