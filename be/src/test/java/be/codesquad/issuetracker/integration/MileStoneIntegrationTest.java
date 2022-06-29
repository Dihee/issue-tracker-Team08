package be.codesquad.issuetracker.integration;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

import be.codesquad.issuetracker.issue.domain.Status;
import be.codesquad.issuetracker.milestone.dto.MileStoneSaveRequest;
import be.codesquad.issuetracker.milestone.service.MileStoneService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDate;
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
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
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
        MileStoneSaveRequest request = new MileStoneSaveRequest("첫번째 테스트용 마일스톤",
            "본문입니다", LocalDate.of(2022, 06, 29), Status.OPEN);
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
}
