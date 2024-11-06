package com.regie.bricotek.userServices;

import com.regie.bricotek.User.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {
    private final UserService userService;

    @GetMapping("getUsers")
    public ResponseEntity<?> getUsers(){
        System.out.println(userService.getUsers());
        return ResponseEntity.ok(userService.getUsers());
    }
}
