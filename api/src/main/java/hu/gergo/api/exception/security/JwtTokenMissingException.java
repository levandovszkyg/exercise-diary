package hu.gergo.api.exception.security;

import io.jsonwebtoken.JwtException;

public class JwtTokenMissingException extends JwtException {
    public JwtTokenMissingException() {
        super("");
    }
    public JwtTokenMissingException(String message) {
        super(message);
    }
}
