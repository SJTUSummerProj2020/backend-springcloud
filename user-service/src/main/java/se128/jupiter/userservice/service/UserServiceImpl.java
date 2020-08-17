package se128.jupiter.userservice.service;

import se128.jupiter.userservice.entity.UserEntity;
import se128.jupiter.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public UserEntity getUserByUserId(Integer userId) {
//        Optional<UserEntity> user = userRepository.findById(userId);
//        return user.orElse(null);
//    }
//
//    @Override
//    public UserEntity getUserByUsernameAndPassword(String username, String password) {
//        return userRepository.getUserByUsernameAndPassword(username, password);
//    }
//
//    @Override
//    public UserEntity addUser(UserEntity userEntity) {
//        return userRepository.saveAndFlush(userEntity);
//    }
//
//    @Override
//    public List<UserEntity> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public UserEntity changeUserStatusByUserId(Integer userId) {
//        Optional<UserEntity> user = userRepository.findById(userId);
//        if(user.isPresent())
//        {
//            user.get().setUserType(-user.get().getUserType());
//            return userRepository.saveAndFlush(user.get());
//        }
//        else {
//            return null;
//        }
//    }
//
//    @Override
//    public UserEntity editUser(UserEntity userEntity) {
//        return userRepository.saveAndFlush(userEntity);
//    }

    public UserEntity getUser(String id) {
        if (!userRepository.existsById(id)) {
            return null;
        }
        return userRepository.findById(id).get();
    }

    public UserEntity saveUser(UserEntity userEntity) {
        if (StringUtils.isEmpty(userEntity)) {
            return null;
        }
        userEntity.setCreateBy("sys");
        userEntity.setUpdateBy("sys");
        return userRepository.save(userEntity);
    }

}
