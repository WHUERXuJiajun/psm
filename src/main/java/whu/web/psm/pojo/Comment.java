package whu.web.psm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@ApiModel(value="评论",description="评论")
public class Comment {

    @Id
    @ApiModelProperty(value="评论id",name="id")
    String id;

    @ApiModelProperty(value="被评论的任务id",name="mid")
    Integer mid;//任务id

    @ApiModelProperty(value="评论内容",name="content")
    String content;//评论内容

    @ApiModelProperty(value="评论用户",name="from_user")
    String from_user;//评论用户

    @ApiModelProperty(value="被评论用户",name="to_user")
    String to_user;//被评论用户

    @ApiModelProperty(value="评论时间",name="comment_time")
    Date comment_time;//评论时间



    public Comment() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFrom_user() {
        return from_user;
    }

    public void setFrom_user(String from_user) {
        this.from_user = from_user;
    }

    public String getTo_user() {
        return to_user;
    }

    public void setTo_user(String to_user) {
        this.to_user = to_user;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }
}
