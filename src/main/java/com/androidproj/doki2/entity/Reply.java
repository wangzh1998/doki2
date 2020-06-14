package com.androidproj.doki2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "reply_id")
    private int replyId;

    private boolean isPublic;

    //int user_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //@CreationTimestamp
    //Timestamp time;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;


    private String replyContent;


    //TODO 一级评论直接与saying相关，level=1,saying!=null,fatherReply=null，secondReplyList!=null,toUser=null;
    //TODO 二级评论                level=2,saying=null，fatherReply!=null，secondReplList=null,toUser!=null
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},optional = false) //false表示saying不能为空
    @JoinColumn(name="saying_id")
    //@JsonIgnoreProperties(value = "replyList")//避免循环查询
    private Saying saying; //int saying_id;//外键，直接在one端(saying)注明，此处不需要声明


    //TODO 默认一级评论层级为1，二级评论层级为2
    private int level = 1;

    //TODO 补充的
    @OneToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "father_reply_id",referencedColumnName = "reply_id")
    @JoinColumn(name = "father_reply_id")
    //@JsonIgnoreProperties(value = "secondReplyList")//避免循环查询
    private Reply fatherReply;

    @OneToOne
    @JoinColumn(name = "reply_user_id")
    private User replyUser;//二级评论中才有的，表示被回复的一级评论用户

    //TODO 补充的
    @OneToMany(mappedBy = "fatherReply",fetch = FetchType.EAGER)
    //@JoinColumn(name = "reply_pid",referencedColumnName = "reply_id")
    @OrderBy("id DESC")
    //@JsonIgnoreProperties(value = "fatherReply")//避免循环查询
    @Where(clause = "level=2")
    private List<Reply> secondReplyList;

    public Reply() {
    }

    public Reply(boolean isPublic, User user, Date time, String replyContent, Saying saying) {
        this.isPublic = isPublic;
        this.user = user;
        this.time = time;
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
                ", saying=" + saying +
                ", level=" + level +
                ", fatherReply=" + fatherReply +
                ", replyUser=" + replyUser +
                ", secondReplyList=" + secondReplyList +
                '}';
    }

    public User getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(User replyUser) {
        this.replyUser = replyUser;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public Saying getSaying() {
        return saying;
    }

    public void setSaying(Saying saying) {
        this.saying = saying;
    }

    public Reply getFatherReply() {
        return fatherReply;
    }

    public void setFatherReply(Reply fatherReply) {
        this.fatherReply = fatherReply;
    }

    public List<Reply> getSecondReplyList() {
        return secondReplyList;
    }

    public void setSecondReplyList(List<Reply> secondReplyList) {
        this.secondReplyList = secondReplyList;
    }

}

