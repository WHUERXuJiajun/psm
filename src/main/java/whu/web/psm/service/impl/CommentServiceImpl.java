package whu.web.psm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whu.web.psm.dao.CommentMapper;
import whu.web.psm.dao.MissionTableMapper;
import whu.web.psm.pojo.Comment;
import whu.web.psm.pojo.MissionTable;
import whu.web.psm.service.CommentService;
import whu.web.psm.service.PostService;
import whu.web.psm.util.UUIDUtil;

import java.util.ArrayList;
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
    @Autowired
    PostService postService;

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

    @Override
    public int getCommentsCount(Integer mid){
        return commentMapper.selectAllByMid(mid).size();
    }

    @Override
    public List<Comment> getMessage(String user){
        List<Comment> comments = new ArrayList<>();
        //获取该用户发布的任务号
        List<MissionTable> missionTables = postService.getMissionsByPhone(user);
        //查询该用户发布的每个任务的评论
        for(MissionTable missionTable:missionTables){
            List<Comment> commentList1 = commentMapper.findByMidAndTo_user(missionTable.getMid(),"");
            comments.addAll(commentList1);
        }
        //查询回复该用户的评论
        List<Comment> commentList2 = commentMapper.selectByUser(user);
        comments.addAll(commentList2);
        return comments;
    }
}
