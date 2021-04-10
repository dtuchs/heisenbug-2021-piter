package ru.heisenbug;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.exactly;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.Matchers.equalTo;

class ApiTest {

    private static final WireMock testMock = new WireMock("localhost", 8080);

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost:8081";
        testMock.resetRequests();
        testMock.register(WireMock.post("/upload")
                .willReturn(aResponse().withStatus(200)));
    }

    @AfterEach
    void tearDown() {
        testMock.resetRequests();
    }

    @ResourceLock("mock")
    @ValueSource(strings = {"Olga", "Petr", "Anna"})
    @ParameterizedTest
    void sendMessageToThirdPartyService(String name) {
        RestAssured.given().when().get("say?name=" + name)
                .then()
                .statusCode(200)
                .assertThat()
                .body(equalTo("{\"name\":\"" + name + "\"}"));

        testMock.verifyThat(exactly(1), postRequestedFor(urlEqualTo("/upload"))
                .withRequestBody(containing(name)));
    }

    @ResourceLock("mock")
    @ValueSource(strings = {"Dima", "Denis", "Dasha"})
    @ParameterizedTest
    void shouldNotSendMessageToThirdPartyService(String name) {
        RestAssured.given().when().get("say?name=" + name)
                .then()
                .statusCode(200)
                .assertThat()
                .body(equalTo("{\"name\":\"" + name + "\"}"));

        testMock.verifyThat(exactly(0), postRequestedFor(urlEqualTo("/upload")));
    }
}
