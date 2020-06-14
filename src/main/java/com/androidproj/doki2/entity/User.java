package com.androidproj.doki2.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User{

    @Id
    //这个注解对应的包是javax.persistence.*，而不是org.springframework.data.annotation.Id。如果搞错了，会有No identifier specified for entity:的报错。
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uniqUserId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int gender;

    @Column(nullable = true)
    private Date birthday;

    @Column(nullable = false)
    private String realName;

    @Column(nullable = false)
    private String nickName;

    @Column (nullable = true)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String userImageSrc;



//    //TODO 这里权限为空
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> auths = new ArrayList();
////        if (this.roleId == -1 || this.roleName==null||this.roleName.isEmpty()) {//this.role == null
////            System.out.println("UserAndRole:role error");
////        } else {
////            auths.add(new SimpleGrantedAuthority());
////        }
//
//        return auths;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    //TODO 这里给userdetails中用的是phone_number
//    @Override
//    public String getUsername() {
//        return phone_number;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }


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

    public String getPassword() {
        return password;
    }

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

