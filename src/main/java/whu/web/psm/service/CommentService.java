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

    /**
     * 更新评论
     * @param comment
     */
    void updateComment(Comment comment);

    /**
     * 根据任务id获取评论数
     *
     * @param mid -- 任务id
     * @return 评论数
     */
    int getCommentsCount(Integer mid);

    /**
     * 获取用户收到的评论消息
     *
     *
     * @param user -- 收到评论消息的用户
     * @return 评论列表
     */
    List<Comment> getMessage(String user);



}
