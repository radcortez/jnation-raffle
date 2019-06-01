package pt.jnation.raffle;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class RaffleResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
            .when().get("/raffle")
            .then()
            .log().all()
            .statusCode(200);

        given()
            .when().get("/raffle")
            .then()
            .log().all()
            .statusCode(200);
    }
}
