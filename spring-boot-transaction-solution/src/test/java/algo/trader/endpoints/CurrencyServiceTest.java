package algo.trader.endpoints;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsArrayContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import algo.trader.Application;
import algo.trader.domain.Currency;
import io.restassured.RestAssured;
import io.restassured.response.Response;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT, classes = { Application.class})
public class CurrencyServiceTest {

	@Value("${local.server.port}")
	private int serverPort;
	
	@Before
	public void init(){
		 RestAssured.port = serverPort;
	}
	
	@Test 
	public void allCurrenciesReturned(){
		Response response = given()
			.accept(MediaType.APPLICATION_JSON_VALUE).
        when().
        	get("/currencies").
        then().
        	statusCode(HttpStatus.SC_OK).
        and().
        	extract().response();
		
		Currency[] jsonResponse = response.as(Currency[].class);
		
		assertArrayEquals(jsonResponse, Currency.values());
    }
}
