package se128.jupiter.userservice.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se128.jupiter.userservice.entity.User;
import se128.jupiter.userservice.repository.UserRepository;
import se128.jupiter.userservice.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getUserByUserId(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.getUserByUsernameAndPassword(username, password);
    }

    @Override
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User changeUserStatusByUserId(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent())
        {
            user.get().setUserType(-user.get().getUserType());
            return userRepository.saveAndFlush(user.get());
        }
        else {
            return null;
        }
    }

    @Override
    public User editUser(User user) {
        return userRepository.saveAndFlush(user);
    }
}
