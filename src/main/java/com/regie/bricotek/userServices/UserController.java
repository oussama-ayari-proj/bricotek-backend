package com.regie.bricotek.userServices;

import com.regie.bricotek.User.User;
import com.regie.bricotek.auth.AuthenticationService;
import com.regie.bricotek.auth.RegistrationRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {
    private final UserService userService;
    @Autowired
    private final AuthenticationService authenticationService;
    @GetMapping("getUsers")
    public ResponseEntity<?> getUsers(){

        return ResponseEntity.ok(userService.getUsers());
    }
    @DeleteMapping("deleteUser")
    public ResponseEntity<?> deleteUser(Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("modifyUser")
    public ResponseEntity<?> modifyUser(Integer id,RegistrationRequest request){
        authenticationService.modifyUser(id,request);
        return ResponseEntity.ok().build();
    }
}
