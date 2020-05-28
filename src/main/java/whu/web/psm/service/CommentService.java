package whu.web.psm.service;
import whu.web.psm.pojo.Comment;

import java.util.List;

public interface CommentService {

    /**
     * 根据任务id获取评论
     *
     * @param mid -- 任务id
     * @return 按时间升序的评论
     */
    List<Comment> getCommentsByMid(Integer mid);

    /**
     * 添加评论
     *
     * @param comment -- 评论
     */
    void postComment(Comment comment);


}