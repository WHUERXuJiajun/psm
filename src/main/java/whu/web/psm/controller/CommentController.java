package whu.web.psm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import whu.web.psm.pojo.Comment;
import whu.web.psm.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/comment")
@Api(value = "CommentController",tags = "评论模块")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping(value = "/{mid}")
    @ApiOperation(
            value = "根据任务id获取评论（已排序）",
            notes = "把mid加到路径上"
    )
    List<Comment> getCommentsByMid(@PathVariable Integer mid) {
        return commentService.getCommentsByMid(mid);
    }

    @GetMapping(value = "/count/{mid}")
    @ApiOperation(
            value = "根据任务id获取评论数量"
    )
    int getCommentsCount(@PathVariable Integer mid) {

        return commentService.getCommentsCount(mid);
    }

    @GetMapping(value = "/user")
    @ApiOperation(
            value = "根据用户名获取收到的评论"
    )
    List<Comment> getMessage(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return commentService.getMessage(principal.getName());
    }

    @PreAuthorize("hasRole('user')")
    @PostMapping
    @ApiOperation(
            value = "添加评论",
            notes = "id和comment_time不需要添加，后台会添加"
    )
   void postComment(@RequestBody Comment comment, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        comment.setFrom_user(principal.getName());
        commentService.postComment(comment);
    }


    @PreAuthorize("hasRole('user')")
    @PutMapping(value = "/thumb")
    @ApiOperation(
            value = "点赞",
            notes = "点赞"
    )
    List<Comment> addThumb(@RequestBody Comment comment) {
        comment.setThumbs(comment.getThumbs()+1);
        commentService.updateComment(comment);
        return commentService.getCommentsByMid(comment.getMid());
    }
}
