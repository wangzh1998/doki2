package com.androidproj.doki2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//所有的实体属性一定要是private，否则无法建表，报错为 Not a managed type
@Entity
public class Saying {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sayingId;

    @OneToOne
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    //@Column(nullable = false) //@Column(s) not allowed on a @OneToOne property: com.androidproj.doki.entity.Saying.to_user
    @OneToOne
    @JoinColumn(name = "to_user_id")
    private User toUser; //to_user可以为空，但是当它不为空时，和to_user_phone_num字段会有冗余

    @Column(nullable = false)
    private String fromUserPhoneNum;

    @Column(nullable = true)
    private String toUserPhoneNum;

    //    @Column(nullable = false)
//    @CreationTimestamp
//    Timestamp time;
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    //@Lob //大对象
    @Column(length = 500)
    private String contents;

    private String sayingImageSrc;
    private boolean isPublic;


    //@Transient
    @OneToMany(mappedBy = "saying",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    //@JsonManagedReference
    ////@JoinColumn(name = "saying_id") //@JoinColum（多端外键）不能和mappedBy（一端）同时出现
    private List<Reply> replyList = new ArrayList<>();


    public Saying() {
    }

    public Saying(User fromUser, String fromUserPhoneNum, Date time, String contents, String sayingImageSrc, boolean isPublic) {
        this.fromUser = fromUser;
        this.fromUserPhoneNum = fromUserPhoneNum;
        this.time = time;
        this.contents = contents;
        this.sayingImageSrc = sayingImageSrc;
        this.isPublic = isPublic;
    }

    @Override
    public String toString() {
        return "Saying{" +
                "sayingId=" + sayingId +
                ", fromUser=" + fromUser +
                ", toUser=" + toUser +
                ", fromUserPhoneNum='" + fromUserPhoneNum + '\'' +
                ", toUserPhoneNum='" + toUserPhoneNum + '\'' +
                ", time=" + time +
                ", contents='" + contents + '\'' +
                ", sayingImageSrc='" + sayingImageSrc + '\'' +
                ", isPublic=" + isPublic +
                ", replyList=" + replyList +
                '}';
    }

    public int getSayingId() {
        return sayingId;
    }

    public void setSayingId(int sayingId) {
        this.sayingId = sayingId;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public String getFromUserPhoneNum() {
        return fromUserPhoneNum;
    }

    public void setFromUserPhoneNum(String fromUserPhoneNum) {
        this.fromUserPhoneNum = fromUserPhoneNum;
    }

    public String getToUserPhoneNum() {
        return toUserPhoneNum;
    }

    public void setToUserPhoneNum(String toUserPhoneNum) {
        this.toUserPhoneNum = toUserPhoneNum;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getSayingImageSrc() {
        return sayingImageSrc;
    }

    public void setSayingImageSrc(String sayingImageSrc) {
        this.sayingImageSrc = sayingImageSrc;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

//    public List<Reply> getReplyList() {
//        return replyList;
//    }
//
//    public void setReplyList(List<Reply> replyList) {
//        this.replyList = replyList;
//    }

}

