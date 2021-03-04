/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package scibite_tech_test.wikidata.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import scibite_tech_test.wikidata.domain.DateBinding;
import scibite_tech_test.wikidata.domain.DateOfBirth;
import scibite_tech_test.wikidata.domain.PlaceOfBirth;
import scibite_tech_test.wikidata.exceptions.RestfulRuntimeException;
import scibite_tech_test.wikidata.transformer.DobToAgeInYrs;


@Component
public class Question {
	
	private static final Logger log = LoggerFactory.getLogger(Question.class);
	private static final String url = "https://query.wikidata.org/sparql?query=";
	private static final Integer failedAge = Integer.valueOf(0);
	private static final String failedPlace = "unknown";
	private static final String selectPart = "select%20?res%20where%20%7B%20?z%20rdfs:label%20";
	private static final String languagePart = "@en%20.%20";
	

	RestTemplate restTemplate = new RestTemplate();
	
	//https://query.wikidata.org/sparql?query=select%20%3Fpob%20%3Fdob%0Awhere%20%7B%0A%3Fz%20rdfs%3Alabel%20%22David%20Cameron%22%40en%20.%0A%3Fz%20wdt%3AP19%20%3Fa%20.%0A%3Fz%20wdt%3AP569%20%3Fdob%20.%0A%3Fa%20wdt%3AP1448%20%3Fpob%0A%7D%0A
	
	/**
	 * Date of birth question format: "How old is <Firstname> <Surname>"
	 * Place of birth question format: "What is the birth place name of <Firstname> <Surname> ?"
	 * @param question Either the date of birth question or place of birth question
	 * @return processed answer to the specific question
	 * @throws RestfulRuntimeException 
	 */
    public static String ask(String question) throws RestfulRuntimeException {
		
		String name = null;
		URI uri = null;
		RestTemplate restTemplate = new RestTemplate();
		if(question.startsWith("What is")) {
			name = question.replaceAll("What is the birth place name of ", "").replaceAll(" \\?", "").replaceAll(" ", "%20");
			uri = buildPlaceQuery.apply(name);
			return consumePlaceResponse.apply(restTemplate.getForEntity(uri, PlaceOfBirth.class));
		} else if(question.startsWith("How old")) {
			name = question.replaceAll("How old is ", "").replaceAll(" ", "%20");
			uri = buildAgeQuery.apply(name);
			return String.valueOf(consumeAgeResponse.apply(restTemplate.getForEntity(uri, DateOfBirth.class)));
		} else {
			log.error("Unknown question type:\n{}",question);
			throw new RestfulRuntimeException("Unknown question type:\n" + question + "\nUnable to complete execution");
		}
	}
	
	
	
	/**
	 * 
	 * @param question Takes a question argument of the form "How old is <Firstname> <Surname>"
	 * @return The age of the person in the question or 0 
	 */
	public Integer askAge(String question) {
		String name = question.replaceAll("How old is ", "").replaceAll(" ", "%20");
		ResponseEntity<DateOfBirth> res = restTemplate.getForEntity(buildAgeQuery.apply(name), DateOfBirth.class);
		return consumeAgeResponse.apply(res);
	}

	/**
	 * 
	 * @param question Takes a question argument of the form "What is the birth place name of <Firstname> <Surname> ?"
	 * @return The birth place of the person in in the question or unknown.
	 */
	public String askPlaceOfBirth(String question) {
		String name = question.replaceAll("What is the birth place name of ", "").replaceAll(" \\?", "").replaceAll(" ", "%20");
		ResponseEntity<PlaceOfBirth> res = restTemplate.getForEntity(buildPlaceQuery.apply(name), PlaceOfBirth.class);
		return consumePlaceResponse.apply(res);
	}
	
	
	
	
	private static Function<ResponseEntity<DateOfBirth>,Integer> consumeAgeResponse = res -> {
		if (res.getStatusCode().is2xxSuccessful()) {
			DateOfBirth data = res.getBody();
			DateBinding date = data.getResults().get(0).getBinding();
			DobToAgeInYrs ageTransformer = new DobToAgeInYrs();
			return ageTransformer.apply(date.getDate());
		} else {
			log.info("Http request to WikiData API failed with error code:{}", res.getStatusCodeValue());
		}
		return failedAge;
	};
	
	
	private static Function<ResponseEntity<PlaceOfBirth>,String> consumePlaceResponse = res -> {
		if (res.getStatusCode().is2xxSuccessful()) {
			PlaceOfBirth data = res.getBody();
			return data.getResults().get(0).getBinding().getLiteral();
		} else {
			log.info("Http request to WikiData API failed with error code:{}", res.getStatusCodeValue());
		}
		return failedPlace;
	};
	
	
	private static Function<String,URI> buildAgeQuery = name -> {
		StringBuilder getQuery = new StringBuilder(url);
		getQuery.append(selectPart).append("%22").append(name).append("%22");
		getQuery.append(languagePart).append("?z%20wdt:P569%20?res%20.%20%7D%20limit%201");
		URI uri = null;
		try {
			uri = new URI(getQuery.toString());
		} catch (URISyntaxException e) {
			log.debug("Something is wrong with the uri string:{}\n{}",uri,e.getMessage());
		}
		
		return uri;
	};
	
	private static Function<String,URI> buildPlaceQuery = name -> {
		StringBuilder getQuery = new StringBuilder(url);
		getQuery.append(selectPart).append("%22").append(name).append("%22");
		getQuery.append(languagePart).append("?z%20wdt:P19%20?a%20.%20?a%20wdt:P1448%20?res%20%7D%20limit%201");
		URI uri = null;
		try {
			uri = new URI(getQuery.toString());
		} catch (URISyntaxException e) {
			log.debug("Something is wrong with the uri string:{}\n{}",uri,e.getMessage());
		}
		
		return uri;
	};
}
