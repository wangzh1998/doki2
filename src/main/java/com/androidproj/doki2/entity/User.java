
package com.androidproj.doki2.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class User{

    @Id
    //这个注解对应的包是javax.persistence.*，而不是org.springframework.data.annotation.Id。如果搞错了，会有No identifier specified for entity:的报错。
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uniqUserId;

    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    private int gender = 0;

    @Column(nullable = true)
    private Date birthday;

    @Column(nullable = false)
    private String realName;

    private String nickName = "defaultNickName";

    @Column (nullable = true)
    private String email;

    //TODO 将这里修改为正确的默认路径
    private String userImageSrc = "defaultUserImageSrc";


    public User(){

    }
    public User(String password, int gender, Date birthday, String realName, String nickName, String phoneNumber, String userImageSrc) {
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.realName = realName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.userImageSrc = userImageSrc;
    }

    @Override
    public String toString() {
        return "User{" +
                "uniqUserId=" + uniqUserId +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", realName='" + realName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userImageSrc='" + userImageSrc + '\'' +
                '}';
    }

    public int getUniqUserId() {
        return uniqUserId;
    }

    public void setUniqUserId(int uniqUserId) {
        this.uniqUserId = uniqUserId;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> auths = new ArrayList();
//        auths.add(new SimpleGrantedAuthority("ROLE_USER"));
//        return auths;
//    }

    public String getPassword() {
        return password;
    }

//    @Override
//    //此处重写userDetails中的getUsername,返回的并不是姓名，而是uk:phoneNumber
//    public String getUsername() {
//        return phoneNumber;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;//账号未过期
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;//账号未被锁定
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true; //当修改密码后，账号凭证会过期
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;//账号被启用
//    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserImageSrc() {
        return userImageSrc;
    }

    public void setUserImageSrc(String userImageSrc) {
        this.userImageSrc = userImageSrc;
    }
}

