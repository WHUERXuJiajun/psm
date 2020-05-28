package whu.web.psm.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Comment {

    @Id
    String id;

    Integer mid;//任务id

    String content;//评论内容

    String from_user;//评论用户

    String to_user;//被评论用户

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
}
