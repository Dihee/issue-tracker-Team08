package be.codesquad.issuetracker.integration;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

import be.codesquad.issuetracker.milestone.dto.MilestoneRequest;
import be.codesquad.issuetracker.milestone.service.MileStoneService;
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
class MileStoneIntegrationTest {

    @Autowired
    private MileStoneService mileStoneService;

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
        MilestoneRequest request = new MilestoneRequest("첫번째 테스트용 마일스톤",
            "본문입니다", null, null);
        mileStoneService.save(request);
    }

    @Test
    void 모든_마일스톤을_조회한다() {
        given(documentationSpec)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-mileStones", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .log().all()

            .when()
            .get("/api/milestones?status=open")

            .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());

    }

    @Test
    void 특정_마일스톤을_조회한다() {
        given(documentationSpec)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-mileStone-detail", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .log().all()

            .when()
            .get("/api/milestones/1")

            .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    void 마일스톤을_생성한다() {
        Map<String, Object> content = Map.of(
            "title", "첫번째 마이르스톤 생성",
            "description", "마이르스톤 코멘트입니다."
        );

        given(documentationSpec)
            .body(content)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("create-mileStone", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .log().all()

            .when()
            .post("/api/milestones")

            .then()
            .log().all()
            .statusCode(HttpStatus.CREATED.value());
    }
}
