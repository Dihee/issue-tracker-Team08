package be.codesquad.issuetracker.oauth;

import be.codesquad.issuetracker.oauth.dto.GithubToken;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {

    private String url = "https://github.com/login/oauth/access_token";
    private String clientId = "ff50ff7342e90de02060";
    private String clientSecret = "deaf964bb0ece843b7703b980ca9a7019874fe38";

    public GithubToken getAccessToken(String code) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json");
        headers.setAll(header);

        MultiValueMap<String, String> requestPayloads = new LinkedMultiValueMap<>();
        Map<String, String> requestPayload = new HashMap<>();
        requestPayload.put("client_id", clientId);
        requestPayload.put("client_secret", clientSecret);
        requestPayload.put("code", code);
        requestPayloads.setAll(requestPayload);

        HttpEntity<?> request = new HttpEntity<>(requestPayloads, headers);
        ResponseEntity<?> response = new RestTemplate().postForEntity(
            url, request, GithubToken.class);
        return (GithubToken) response.getBody();
    }
}
