package se128.jupiter.usercenter.dto;

public class UserDto {

    private Integer userId;
    private String username;
    private String nickname;
    private String password;
    private String phone;
    private Integer userType = 1;
    private Integer buy0 = 0;
    private Integer buy1 = 0;
    private Integer buy2 = 0;
    private Integer buy3 = 0;
    private Integer buy4 = 0;
    private Integer buy5 = 0;

    public Integer getUserId() {
        return this.userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return this.userType;
    }
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getBuy0() {
        return buy0;
    }
    public void setBuy0(Integer buy0) {
        this.buy0 = buy0;
    }

    public Integer getBuy1() {
        return buy1;
    }
    public void setBuy1(Integer buy1) {
        this.buy1 = buy1;
    }

    public Integer getBuy2() {
        return buy2;
    }
    public void setBuy2(Integer buy2) {
        this.buy2 = buy2;
    }

    public Integer getBuy3() {
        return buy3;
    }
    public void setBuy3(Integer buy3) {
        this.buy3 = buy3;
    }

    public Integer getBuy4() {
        return buy4;
    }
    public void setBuy4(Integer buy4) {
        this.buy4 = buy4;
    }

    public Integer getBuy5() {
        return buy5;
    }
    public void setBuy5(Integer buy5) {
        this.buy5 = buy5;
    }
}
