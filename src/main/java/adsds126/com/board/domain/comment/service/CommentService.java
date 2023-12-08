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

import javax.persistence.EntityNotFoundException;
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

    public Comment createComment(Long boardSeq, Long userSeq, CommentDto.Post commentDto) {
        Board board = boardRepository.findById(boardSeq)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with ID: " + boardSeq));

        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userSeq));

        Comment newComment = new Comment();
        newComment.setText(commentDto.getText());
        newComment.setBoard(board);
        newComment.setUser(user);
        newComment.setRegDate(LocalDateTime.now());
        newComment.setUptDate(LocalDateTime.now());

        return commentRepository.save(newComment);
    }

    public Comment updateComment(Long commentId, CommentDto.Update commentDto) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with ID: " + commentId));

        existingComment.setText(commentDto.getText());
        existingComment.setUptDate(LocalDateTime.now());

        return commentRepository.save(existingComment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.findById(commentId)
                .ifPresent(commentRepository::delete);
    }

    public List<Comment> getAllCommentsByBoardId(Long boardSeq) {
        return commentRepository.findAllByBoard_BoardSeq(boardSeq);
    }
}

