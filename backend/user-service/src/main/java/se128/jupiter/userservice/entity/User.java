package se128.jupiter.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class User {

    @Id
    @Column(name = "user_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Basic
    @Column(name = "user_name")
    private String username;

    @Basic
    @Column(name = "password")
    private String password;

    private String phone;
    private Integer userType;

    private Integer buy0;
    private Integer buy1;
    private Integer buy2;
    private Integer buy3;
}
