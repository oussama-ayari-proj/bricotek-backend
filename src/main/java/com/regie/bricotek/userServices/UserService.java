package com.regie.bricotek.userServices;

import com.regie.bricotek.User.User;
import com.regie.bricotek.User.UserRepository;
import com.regie.bricotek.User.UserResponse;
import com.regie.bricotek.auth.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(UserResponse::fromEntity).toList();
    }

    public void deleteUser(Integer id){
        if(userRepository.findByUserId(id).isPresent()){
            User user = userRepository.findByUserId(id).get();
            System.out.println(user.getEmail());
            userRepository.delete(user);
        }
    }

}
