package cinema.entity;

import java.util.UUID;

public class Token {
    private UUID token;

    public Token() {
        this.token = UUID.randomUUID();
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
