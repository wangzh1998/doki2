package com.androidproj.doki2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int replyId;

    private boolean isPublic;

    @OneToOne//不是@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private Date time;


    private String replyContent;


    //一级评论直接与saying相关,二级评论只与一级评论相关
    //@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},optional = false,fetch = FetchType.EAGER) //false表示saying不能为空
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.LAZY) //false表示saying不能为空
    //@JsonBackReference
    @JoinColumn(name="saying_id",nullable = false)
    @JsonIgnoreProperties(value = "replyList")//避免循环查询
    private Saying saying; //int saying_id;//外键，直接在one端(saying)注明，此处不需要声明


    @OneToMany(mappedBy = "fatherReply",fetch = FetchType.EAGER)
    private List<SecondReply> secondReplyList;

    public Reply() {
    }

    public Reply(boolean isPublic, User user, String replyContent, Saying saying) {
        this.isPublic = isPublic;
        this.user = user;
        this.replyContent = replyContent;
        this.saying = saying;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", isPublic=" + isPublic +
                ", user=" + user +
                ", time=" + time +
                ", replyContent='" + replyContent + '\'' +
                ", secondReplyList=" + secondReplyList +
                '}';
    }



    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
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

//    public Saying getSaying() {
//        return saying;
//    }

    public void setSaying(Saying saying) {
        this.saying = saying;
    }


    public List<SecondReply> getSecondReplyList() {
        return secondReplyList;
    }

    public void setSecondReplyList(List<SecondReply> secondReplyList) {
        this.secondReplyList = secondReplyList;
    }

}

