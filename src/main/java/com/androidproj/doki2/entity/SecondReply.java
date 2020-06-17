package com.androidproj.doki2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
public class SecondReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int secondReplyId;

    private boolean isPublic;

    //int user_id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    //@CreationTimestamp
    //Timestamp time;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;


    private String replyContent;

    //TODO 补充的
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinColumn(name = "father_reply_id")
    private Reply fatherReply;

    @OneToOne
    @JoinColumn(name = "reply_user_id")
    private User replyUser;//二级评论中才有的，表示被回复的一级评论用户


    public SecondReply() {
    }


    @Override
    public String toString() {
        return "Reply{" +
                "secondReplyId=" + secondReplyId +
                ", isPublic=" + isPublic +
                ", user=" + user +
                ", time=" + time +
                ", replyContent='" + replyContent + '\'' +
                ", replyUser=" + replyUser +
                '}';
    }

    public User getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(User replyUser) {
        this.replyUser = replyUser;
    }


    public int getSecondReplyId() {
        return secondReplyId;
    }

    public void setSecondReplyId(int secondReplyId) {
        this.secondReplyId = secondReplyId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }



//    public Reply getFatherReply() {
//        return fatherReply;
//    }

    public void setFatherReply(Reply fatherReply) {
        this.fatherReply = fatherReply;
    }


}

