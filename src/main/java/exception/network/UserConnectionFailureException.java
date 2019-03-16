package exception.network;

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
