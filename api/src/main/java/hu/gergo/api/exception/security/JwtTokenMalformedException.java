package hu.gergo.api.exception.security;

import io.jsonwebtoken.JwtException;

public class JwtTokenMalformedException extends JwtException {
    public JwtTokenMalformedException() {
        super("");
    }
    public JwtTokenMalformedException(String message) {
        super(message);
    }
}
