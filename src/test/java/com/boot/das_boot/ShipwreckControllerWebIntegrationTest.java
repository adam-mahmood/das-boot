package com.boot.das_boot;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ShipwreckControllerWebIntegrationTest {
	@Test
	public void testListAll() throws IOException {
		TestRestTemplate resttemplate = new TestRestTemplate();

		String str = "http://localhost:8080/api/v1/shipwrecks";
		// move url to application-test.properties
		ResponseEntity<String> response = resttemplate.getForEntity(URI.create(str), String.class);

		assertThat(response, is(notNullValue()));
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode responseJson = objectMapper.readTree(response.getBody());

		assertThat(responseJson, is(notNullValue()));
		assertThat(responseJson.isMissingNode(), is(false));
		assertThat(responseJson.toString(), equalTo("[]"));

	}
}
