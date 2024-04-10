package utilities;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;

import java.net.URI;
import java.util.Map;

public class RestAssuredExtension {
    public static RequestSpecification specification;

    public RestAssuredExtension() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder
                .setBaseUri("http://localhost:8080/api/v1/task/get/3")
                .setContentType(ContentType.JSON);

        specification = RestAssured.given().spec(builder.build());
    }

    @SneakyThrows
    public static void getOpsWithPathParameter(String url, Map<String, String> pathParams) {
        specification.pathParams(pathParams);
        specification.get(new URI(url));
    }

    @SneakyThrows
    public static ResponseOptions<Response> getOps(String url) {
        return specification.get(new URI(url));
    }

    @SneakyThrows
    public static ResponseOptions<Response> postOpsWithBody(String url,
                                                            Map<String, String> body) {
        specification.body(body);
        return specification.post(new URI(url));
    }
}
