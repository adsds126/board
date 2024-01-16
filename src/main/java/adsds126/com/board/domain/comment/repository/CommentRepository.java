package adsds126.com.board.domain.comment.repository;

import adsds126.com.board.domain.comment.dto.CommentDto;
import adsds126.com.board.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(Long commentSeq);
    //List<Comment> findAllBoardByBoardSeq(Long boardSeq);

    List<Comment> findAllByBoard_BoardSeq(Long boardSeq);
}
