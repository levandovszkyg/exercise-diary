package hu.gergo.api.service.security;

import hu.gergo.api.exception.security.JwtTokenMalformedException;
import hu.gergo.api.model.AppUser;
import hu.gergo.api.model.security.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();

        AppUser parsedUser = jwtUtil.parseToken(token);

        if (parsedUser == null) {
            throw new JwtTokenMalformedException();
        }

        AppUserDetails userDetails = new AppUserDetails(parsedUser);
        List<GrantedAuthority> authorities = new ArrayList<>();

        parsedUser.getRoles().stream().forEach( role -> authorities.add(new SimpleGrantedAuthority(role)));
        userDetails.setAuthorities(authorities);

        jwtAuthenticationToken.setDetails(userDetails);
        return jwtAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

}