package be.codesquad.issuetracker.oauth.service;

import be.codesquad.issuetracker.oauth.dto.GithubUser;
import be.codesquad.issuetracker.user.domain.User;
import be.codesquad.issuetracker.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private static final int EXPIRED_SECOND = 24 * 60 * 60;

    private final UserRepository userRepository;

    @Transactional
    public User upsertUser(GithubUser githubUser) {
        User user = githubUser.toEntity();
        User findUser = userRepository.findByAuthId(user.getAuthId())
            .map(u -> u.update(user))
            .orElse(user);
        return userRepository.save(findUser);
    }

    @Transactional(readOnly = true)
    public String getJwtToken(GithubUser githubUser) {
        User user = upsertUser(githubUser);
        return JwtFactory.create(user, EXPIRED_SECOND);
    }
}
