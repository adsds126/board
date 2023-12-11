package adsds126.com.board.domain.comment.controller;

import adsds126.com.board.domain.comment.dto.CommentDto;
import adsds126.com.board.domain.comment.entity.Comment;
import adsds126.com.board.domain.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Comment> createComment(
            @RequestParam Long boardId,
            @RequestParam Long userId,
            @RequestBody CommentDto.Post commentDto) {
        Comment createdComment = commentService.createComment(boardId, userId, commentDto);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PatchMapping("/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentDto.Update commentDto) {
        Comment updatedComment = commentService.updateComment(commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/board/{boardId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Comment>> getAllCommentsByBoardId(@PathVariable Long boardId) {
        List<Comment> comments = commentService.getAllCommentsByBoardId(boardId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
