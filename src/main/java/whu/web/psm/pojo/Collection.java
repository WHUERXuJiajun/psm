package whu.web.psm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ApiModel(value="收藏列表",description="收藏任务")
public class Collection {

    @Id
    @ApiModelProperty(value = "收藏id", name = "id")
    String id;

    @ApiModelProperty(value = "用户", name = "phone")
    String phone;//用户

    @ApiModelProperty(value = "被评论的任务id", name = "mid")
    Integer mid;//任务id

    public Collection() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}