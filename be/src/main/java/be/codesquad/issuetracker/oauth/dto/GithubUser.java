package be.codesquad.issuetracker.oauth.dto;

import be.codesquad.issuetracker.user.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GithubUser {

    @JsonProperty("node_id")
    private String authId;

    @JsonProperty("login")
    private String username;

    @JsonProperty("avatar_url")
    private String imageUrl;

    public User toEntity() {
        return new User(authId, username, imageUrl);
    }
}
