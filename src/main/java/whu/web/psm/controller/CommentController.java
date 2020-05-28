package whu.web.psm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import whu.web.psm.pojo.Comment;
import whu.web.psm.service.CommentService;

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


    @PreAuthorize("hasRole('user')")
    @PostMapping
    @ApiOperation(
            value = "添加评论",
            notes = "id和comment_time不需要添加，后台会添加"
    )
   void postComment(@RequestBody Comment comment) {
        commentService.postComment(comment);
    }
}
