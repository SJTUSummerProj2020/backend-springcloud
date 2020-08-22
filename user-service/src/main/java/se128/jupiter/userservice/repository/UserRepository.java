package se128.jupiter.userservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se128.jupiter.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Query(value = "from UserEntity where username = :username and password = :password")
    UserEntity getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    UserEntity getUserEntityByUsername(String username);
}
