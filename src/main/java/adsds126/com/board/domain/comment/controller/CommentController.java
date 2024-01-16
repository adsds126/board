package adsds126.com.board.domain.comment.controller;

import adsds126.com.board.domain.comment.dto.CommentDto;
import adsds126.com.board.domain.comment.entity.Comment;
import adsds126.com.board.domain.comment.service.CommentService;
import adsds126.com.board.oauth.entity.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Comment> createComment(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody CommentDto.Post commentDto) {
        Comment createdComment = commentService.createComment(commentDto.getBoardSeq(), userPrincipal.getUserId(), commentDto);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PatchMapping("/{commentSeq}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Long commentSeq,
            @RequestBody CommentDto.Update commentDto) {
        Comment updatedComment = commentService.updateComment(commentSeq, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{commentSeq}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/board/{boardSeq}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Comment>> getAllCommentsByBoardId(@PathVariable Long boardSeq) {
        List<Comment> comments = commentService.getAllCommentsByBoardSeq(boardSeq);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
