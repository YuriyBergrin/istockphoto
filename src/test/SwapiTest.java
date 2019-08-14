

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class SwapiTest {

    @Test(description = "test swapi")
    public void checkPersonAndPlanet() {
        //проверить, что существует персонаж “Luke Skywalker” c home planet Tatooine
        given().when().get("http://swapi.co/api/people/").then().
                assertThat().body("results.name", hasItem("Luke Skywalker"), "results.homeworld", hasItem("https://swapi.co/api/planets/1/"));

        //Проверить что количество персонажей соответствует 87
        expect().body("count", equalTo(87)).when().get("http://swapi.co/api/people/");

        //- Проверить что первые 3 персонажа это Luke Skywalker, "C-3PO и R2-D2
        given().when().get("http://swapi.co/api/people/").then().
                assertThat().body("results.name[0]", equalTo("Luke Skywalker"));

        given().when().get("http://swapi.co/api/people/").then().
                assertThat().body("results.name[1]", equalTo("C-3PO"));

        given().when().get("http://swapi.co/api/people/").then().
                assertThat().body("results.name[2]", equalTo("R2-D2"));
    }

}
