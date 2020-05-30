package whu.web.psm.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import whu.web.psm.pojo.Comment;
import whu.web.psm.util.UUIDUtil;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;

    @Test
    public void getCommentsByMid() {
        assertNotNull(commentService.getCommentsByMid(1));
    }

    @Test
    public void postComment() {
        Comment comment = new Comment();
        comment.setMid(1);
        comment.setContent("这是测试评论");
        comment.setFrom_user("test");
        commentService.postComment(comment);
    }

    @Test
    public void updateComment() {
        Comment comment = new Comment();
        comment.setMid(1);
        comment.setContent("这是测试评论");
        comment.setFrom_user("test");
        commentService.updateComment(comment);
    }
}