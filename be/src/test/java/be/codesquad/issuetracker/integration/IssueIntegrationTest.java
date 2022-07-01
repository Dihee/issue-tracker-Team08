package be.codesquad.issuetracker.integration;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

import be.codesquad.issuetracker.assignee.domain.Assignee;
import be.codesquad.issuetracker.issue.service.IssueService;
import be.codesquad.issuetracker.label.domain.Label;
import be.codesquad.issuetracker.milestone.service.MileStoneService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import java.util.ArrayList;
import java.util.List;
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
class IssueIntegrationTest {

    @Autowired
    private IssueService issueService;

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
        List<Label> labels = new ArrayList<>();
        List<Assignee> assignees = new ArrayList<>();
//        MileStoneSaveRequest mileStoneSaveRequest = new MileStoneSaveRequest("마일스톤 타이틀",
//            "마일스톤 상세내용", LocalDate.of(2022, 06, 29), Status.OPEN);
//        MileStone mileStone = new MileStone(mileStoneSaveRequest);
//        mileStoneService.save(mileStone);\
//        Issue issue = new Issue(null, null, null, "첫번째 이슈 테스트", "이슈 코멘트");

//        IssueSaveRequest request = new IssueSaveRequest("첫번째 이슈 테스트", "이슈 코멘트", null);
//        issueService.save(request);
    }

    @Test
    void 오픈_상태의_이슈를_조회한다() {
        given(documentationSpec)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-issues", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .log().all()

            .when()
            .get("/api/issues?status=open")

            .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());
//            .assertThat()
//            .body("issues", hasSize(1));


    }

    @Test
    void 이슈를_생성한다() {
        Map<String, Object> content = Map.of(
            "subject", "첫번째 이슈 생성",
            "comment", "이슈 코멘트입니다."
        );
        given(documentationSpec)
            .body(content)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("save-issues", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))

            .when()
            .post("/api/issues")

            .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void 특정_이슈를_조회한다() {
        given(documentationSpec)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-issue", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))

            .when()
            .post("/api/issues/1")

            .then()
            .statusCode(HttpStatus.OK.value());
    }
}
