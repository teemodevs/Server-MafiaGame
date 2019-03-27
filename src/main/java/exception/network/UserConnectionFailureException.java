package exception.network;

/**
 * Server Connection 실패 시 오류
 */
public class UserConnectionFailureException extends RuntimeException {
    private String message;

    public UserConnectionFailureException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
