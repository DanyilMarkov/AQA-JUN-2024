package org.prog.testng;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.prog.dto.ResponseDto;
import org.prog.dto.PersonDto;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
public class RestTests {

    //TODO: add location to included fields
    //https://randomuser.me/api/?inc=gender,name,nat,location&noinfo
    //TODO: add DTOs for location
    //TODO: add assert that results.location.city is not null for each person
    @Test
    public void getRandomUser() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.queryParam("inc", "gender,name,location,nat");
        requestSpecification.queryParam("noinfo");
        requestSpecification.queryParam("results", 10);
        requestSpecification.baseUri("https://randomuser.me/");
        requestSpecification.basePath("/api");

        Response response = requestSpecification.get();
        response.prettyPrint();

        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);
//        validatableResponse.body("results[0].gender", Matchers.equalTo("female"));

//        List<String> genders = response.jsonPath().get("results.gender");
//        Assert.assertTrue(genders.contains("female"));
        List<String> cities = response.jsonPath().get("results.location.city");
        Assert.assertNotNull(cities);

        ResponseDto dto = response.as(ResponseDto.class);
        Assert.assertEquals(dto.getResults().size(), 10,
                "Requested 10 results but was " + dto.getResults().size());
    }
}
