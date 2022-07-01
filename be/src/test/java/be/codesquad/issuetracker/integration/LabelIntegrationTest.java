package be.codesquad.issuetracker.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

import be.codesquad.issuetracker.label.dto.LabelSaveRequest;
import be.codesquad.issuetracker.label.service.LabelService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LabelIntegrationTest {

    @Autowired
    LabelService labelService;

    @LocalServerPort
    int port;

    RequestSpecification documentationSpec;

    @BeforeEach
    void setup(RestDocumentationContextProvider restDocumentation) {
        RestAssured.port = port;
        documentationSpec = new RequestSpecBuilder()
            .addFilter(documentationConfiguration(restDocumentation))
            .build();
    }

    @BeforeEach
    void setData() {
        labelService.save(new LabelSaveRequest("첫번째 라벨 테스트", "본문 내용", "#color"));
    }


    @Test
    void 특정_라벨을_조회한다() {
        given(documentationSpec)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-label-detail", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .log().all()

            .when()

            .get("/api/labels/1")

            .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    void 모든_라벨을_조회한다() {
        given(documentationSpec)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-labels", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .log().all()

            .when()

            .get("/api/labels")

            .then()
            .log().all()
            .statusCode(HttpStatus.OK.value())
            .body("labels", hasSize(1));

    }

    @Test
    void 라벨을_생성한다() {
        Map<String, Object> content = Map.of(
            "title", "첫번째 라벨 생성",
            "description", "테스트 라벨 내용입니다.",
            "color", "#color"
        );

        given(documentationSpec)
            .body(content)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("create-issues", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .log().all()

            .when()

            .post("/api/labels")

            .then()
            .log().all()
            .statusCode(HttpStatus.CREATED.value());

    }
}
