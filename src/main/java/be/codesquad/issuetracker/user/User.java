package be.codesquad.issuetracker.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@Entity
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authId;
    private String username;
    private String imageUrl;

    public User(String authId, String username, String imageUrl) {
        this.authId = authId;
        this.username = username;
        this.imageUrl = imageUrl;
    }

    public User update(@NonNull User user) {
        this.authId = user.getAuthId();
        this.username = user.getUsername();
        this.imageUrl = user.getImageUrl();
        return this;
    }
}
