package be.codesquad.issuetracker.issue.domain;

public enum Status {
    OPEN, CLOSED;

    public static Status of(String status) {
        return Status.valueOf(status.toUpperCase());
    }
}
