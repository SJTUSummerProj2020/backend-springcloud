package se128.jupiter.userservice.service;

import se128.jupiter.userservice.entity.UserEntity;
import se128.jupiter.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUser(Integer id) {
        if (!userRepository.existsById(id)) {
            return null;
        }
        return userRepository.findById(id).get();
    }

    public UserEntity saveUser(UserEntity userEntity) {
        if (StringUtils.isEmpty(userEntity)) {
            return null;
        }
        userEntity.setUserType(1);
        userEntity.setBuy0(0);
        userEntity.setBuy1(0);
        userEntity.setBuy2(0);
        userEntity.setBuy3(0);
        userEntity.setBuy4(0);
        userEntity.setBuy5(0);
        return userRepository.save(userEntity);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity changeUserStatusByUserId(Integer userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isPresent())
        {
            user.get().setUserType(-user.get().getUserType());
            return userRepository.saveAndFlush(user.get());
        }
        else {
            return null;
        }
    }

    public UserEntity editUser(UserEntity userEntity) {
        return userRepository.saveAndFlush(userEntity);
    }


    public UserEntity login(UserEntity userEntity) {
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        UserEntity user1 = userRepository.getUserByUsernameAndPassword(username, password);
        return user1;
    }
}
