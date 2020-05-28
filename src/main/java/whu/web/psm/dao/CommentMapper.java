package whu.web.psm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import whu.web.psm.pojo.Comment;

import java.util.List;

public interface CommentMapper extends JpaRepository<Comment, String> {

    @Query(value="select * from comment where mid = :mid order by comment_time ASC",nativeQuery=true)
    List<Comment> selectAllByMid(@Param("mid")Integer mid);
}
