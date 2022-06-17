package be.codesquad.issuetracker.oauth;

import be.codesquad.issuetracker.oauth.dto.GithubToken;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/api/oauth/github")
    public ResponseEntity<String> githubLogin(@PathParam("code") String code, HttpServletResponse response) {
        log.debug("code: {}", code);
        GithubToken githubToken = loginService.getAccessToken(code);
        response.setHeader("Authorization", githubToken.getAuthorizationValue());
        return ResponseEntity.ok("logined");
    }
}
