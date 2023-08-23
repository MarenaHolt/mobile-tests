package helpers;

import config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;


public class Browserstack {
    // curl -u "qagurumobile_piB8or:dqCgQsyscHYt4iEy2vUi" -X
    // GET "https://api.browserstack.com/app-automate/sessions/c48a81741d51697643828028e94f3dab4e481b08.json"

    public static String videoUrl(String sessionId) {
        BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(config.getUser(), config.getKey())
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}
