package assignments;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;


public class ApiAutomation {
	@Test
     void verifyBPIs() {
		 given()
        .when()
        .get("https://api.coindesk.com/v1/bpi/currentprice.json")
        .then()
            .statusCode(200) // Verify HTTP status code is 200
            .body("bpi.size()", equalTo(3)) // Verify there are 3 BPIs
            .body("bpi.USD.code", equalTo("USD")) // Verify USD code
            .body("bpi.GBP.code", equalTo("GBP")) // Verify GBP code
            .body("bpi.EUR.code", equalTo("EUR")) // Verify EUR code
            .body("bpi.GBP.description", equalTo("British Pound Sterling")) // Verify GBP description
            .log().all();   
    
	}
}