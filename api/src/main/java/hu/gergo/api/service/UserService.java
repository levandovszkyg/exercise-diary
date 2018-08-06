package hu.gergo.api.service;

import hu.gergo.api.model.AppUser;
import hu.gergo.api.repository.UserRepository;
import hu.gergo.api.service.security.JwtUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String email, String password){
        AppUser user = userRepository.findByEmail(email);

        if(user == null) {
            throw new BadCredentialsException("Bad credentials.");
        }

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Bad credentials.");
        }

        return jwtUtil.generateToken(user);
    }

    public void signUp(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
