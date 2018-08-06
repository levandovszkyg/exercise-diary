package hu.gergo.api.service.security;

import hu.gergo.api.exception.security.JwtTokenMissingException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader("Authorization");

        try {
            if (header == null || !header.startsWith("Bearer ")) {
                throw new JwtTokenMissingException();
            } else {
                String authToken = header.substring(7);
                Authentication authRequest = jwtAuthenticationProvider.authenticate(new JwtAuthenticationToken(authToken));
                SecurityContextHolder.getContext().setAuthentication(authRequest);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        } catch (JwtException e) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write("{\"message\": \"Bad credentials\"}");
        }
    }
}
