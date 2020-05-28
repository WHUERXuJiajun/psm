package whu.web.psm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import whu.web.psm.pojo.Comment;

public interface CommentMapper extends JpaRepository<Comment, String> {
}
