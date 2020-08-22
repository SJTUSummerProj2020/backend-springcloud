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
        if (userRepository.getUserEntityByUsername(userEntity.getUsername())!= null)
        {
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
        Optional<UserEntity> user = userRepository.findById(userEntity.getUserId());
        if(user.isPresent())
        {
            user.get().setNickname(userEntity.getNickname());
            user.get().setPhone(userEntity.getPhone());
            user.get().setPassword(userEntity.getPassword());
            return userRepository.saveAndFlush(user.get());
        }
        else {
            return null;
        }
    }


    public UserEntity checkUser(String username,String password) {
        return userRepository.getUserByUsernameAndPassword(username, password);
    }

    public UserEntity banUser(Integer id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isPresent())
        {
            user.get().setUserType(-1);
            return userRepository.saveAndFlush(user.get());
        }
        else {
            return null;
        }
    }

    public UserEntity unbanUser(Integer id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isPresent())
        {
            user.get().setUserType(1);
            return userRepository.saveAndFlush(user.get());
        }
        else {
            return null;
        }
    }

    public UserEntity addBuy(Integer id, Integer goodsType) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isPresent())
        {
            switch (goodsType)
            {
                case 0:user.get().setBuy0(user.get().getBuy0()+1);break;
                case 1:user.get().setBuy1(user.get().getBuy1()+1);break;
                case 2:user.get().setBuy2(user.get().getBuy2()+1);break;
                case 3:user.get().setBuy3(user.get().getBuy3()+1);break;
                case 4:user.get().setBuy4(user.get().getBuy4()+1);break;
                case 5:user.get().setBuy5(user.get().getBuy5()+1);break;
            }
            return userRepository.saveAndFlush(user.get());
        }
        else {
            return null;
        }
    }
}
