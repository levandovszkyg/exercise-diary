package hu.gergo.api.controller;

import hu.gergo.api.model.AppUser;
import hu.gergo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(String email, String password) {
        return userService.login(email,password);
    }

    @PostMapping("/signup")
    public void signup(@RequestBody AppUser user) {
        userService.signUp(user);
    }

    @GetMapping("/api/test")
    public String test(){
        return "token works";
    }

}
