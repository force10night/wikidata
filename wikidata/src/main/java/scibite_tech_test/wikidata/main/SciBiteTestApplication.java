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
package scibite_tech_test.wikidata.main;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scibite_tech_test.wikidata.exceptions.RestfulRuntimeException;
import scibite_tech_test.wikidata.service.Question;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class SciBiteTestApplication implements CommandLineRunner {

	// Simple example shows how a command line spring application can execute an
	// injected bean service. Also demonstrates how you can use @Value to inject
	// command line args ('--name=whatever') or application properties

	private static final Logger log = LoggerFactory.getLogger(SciBiteTestApplication.class);
	
	@Bean
	public CommandLineRunner run() throws Exception {
		return args -> {
			log.info("Executing simple command line runner to answer test questions");
			System.out.println("How old is David Cameron " + Question.ask("How old is David Cameron"));
			System.out.println("How old is Tony Blair " + Question.ask("How old is Tony Blair"));
			System.out.println("What is the birth place name of Tony Blair ? " + Question.ask("What is the birth place name of Tony Blair ?"));
			log.info("Execution complete");
		};
	}
	
	
	@Override
	public void run(String... args) {
		try {
			System.out.println(Question.ask("How old is David Cameron"));
			System.out.println(Question.ask("How old is Tony Blair"));
			System.out.println(Question.ask("What is the birth place name of Tony Blair ?"));
		} catch (RestfulRuntimeException e) {
			log.error(e.getMessage());
		}
		
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SciBiteTestApplication.class, args);
	}
}
