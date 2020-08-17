package se128.jupiter.userservice.repository;

import se128.jupiter.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<UserEntity, String> {

//    @Query(value = "from UserEntity where username = :username and password = :password")
//    UserEntity getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
//
//    UserEntity getUserByUserId(Integer userId);
//
//    UserEntity getUserByUsername(String username);
}
