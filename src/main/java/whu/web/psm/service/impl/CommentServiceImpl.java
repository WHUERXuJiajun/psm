package whu.web.psm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whu.web.psm.dao.CommentMapper;
import whu.web.psm.pojo.Comment;
import whu.web.psm.service.CommentService;
import whu.web.psm.util.UUIDUtil;

import java.util.Date;
import java.util.List;

/**
 *
 * @author     : xsy
 * @description: 评论服务
 * @date       : 2020/5/28
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentsByMid(Integer mid) {
        return commentMapper.selectAllByMid(mid);
    }

    @Override
    public void postComment(Comment comment) {
        comment.setId(UUIDUtil.getId());
        comment.setComment_time(new Date());
        comment.setThumbs(0);
        commentMapper.save(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        commentMapper.save(comment);
    }
}
