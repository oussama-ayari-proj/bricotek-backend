package com.regie.bricotek.userServices;

import com.regie.bricotek.User.User;
import com.regie.bricotek.User.UserRepository;
import com.regie.bricotek.User.UserResponse;
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
}
