package se128.jupiter.ssoserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se128.jupiter.ssoserver.entity.UserEntity;
import se128.jupiter.ssoserver.repository.UserRepository;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    public UserEntity checkUser(String username, String password) {
        return userRepository.getUserByUsernameAndPassword(username, password);
    }
}
