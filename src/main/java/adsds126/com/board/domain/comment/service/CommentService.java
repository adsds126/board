package adsds126.com.board.domain.comment.service;

import adsds126.com.board.domain.board.entity.Board;
import adsds126.com.board.domain.board.repository.BoardRepository;
import adsds126.com.board.domain.comment.dto.CommentDto;
import adsds126.com.board.domain.comment.entity.Comment;
import adsds126.com.board.domain.comment.repository.CommentRepository;
import adsds126.com.board.domain.user.entity.User;
import adsds126.com.board.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(
            CommentRepository commentRepository,
            BoardRepository boardRepository,
            UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public Comment createComment(Long boardSeq, String userId, CommentDto.Post commentDto) {
        Board board = boardRepository.findById(boardSeq)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with ID: " + boardSeq));
        User user = userRepository.findByUserId(userId);
        Comment newComment = new Comment();
        newComment.setText(commentDto.getText());
        newComment.setBoard(board);
        newComment.setUser(user);
        newComment.setRegDate(LocalDateTime.now());
        newComment.setUptDate(LocalDateTime.now());

        newComment = commentRepository.save(newComment);
        return newComment;
    }

    public Comment updateComment(Long commentSeq, CommentDto.Update commentDto) {
        Comment existingComment = commentRepository.findById(commentSeq)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with ID: " + commentSeq));

        existingComment.setText(commentDto.getText());
        existingComment.setUptDate(LocalDateTime.now());

        return commentRepository.save(existingComment);
    }

    public void deleteComment(Long commentSeq) {
        commentRepository.findById(commentSeq)
                .ifPresent(commentRepository::delete);
    }

    public List<Comment> getAllCommentsByBoardSeq(Long boardSeq) {
        return commentRepository.findAllByBoard_BoardSeq(boardSeq);
    }
}

