package scibite_tech_test.wikidata.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;


/*
 * POJO for data returned from wikidata queries
 * 
 * Example XML:
 * 
 * <?xml version='1.0' encoding='UTF-8'?>
<sparql xmlns='http://www.w3.org/2005/sparql-results#'>
	<head><variable name='a'/></head>
	<results><result><binding name='a'>
				<literal datatype='http://www.w3.org/2001/XMLSchema#dateTime'>1953-05-06T00:00:00Z</literal>
			</binding></result></results>
</sparql>

 *
 */
@JacksonXmlRootElement(namespace = "xmlns:'http://www.w3.org/2005/sparql-results#'", localName = "sparql")
public @Data class WikiDataSparql {
	@JacksonXmlProperty(localName = "head")
	private Head head;
}
