package adsds126.com.board.domain.board.controller;

import adsds126.com.board.domain.board.dto.BoardDto;
import adsds126.com.board.domain.board.entity.Board;
import adsds126.com.board.domain.board.repository.BoardRepository;
import adsds126.com.board.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/boards")
public class BoardController {

    private BoardService boardService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Board> createBoard(
            @RequestParam("userId") String userId,
            @RequestBody @Valid BoardDto.Post boardDto) {
        Board createdBoard = boardService.createBoard(userId, boardDto);
        return new ResponseEntity<>(createdBoard, HttpStatus.CREATED);
    }

    @PatchMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Board> updateBoard(
            @RequestBody @Valid BoardDto.Update boardDto) {
        Board updatedBoard = boardService.updateBoard(boardDto);
        return new ResponseEntity<>(updatedBoard, HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardSeq) {
        boardService.deleteBoard(boardSeq);
        return ResponseEntity.ok("Board deleted successfully");
    }
//    @GetMapping
//    @PreAuthorize("isAuthenticated()")
//    public ResponseEntity<?> getBoard(@RequestParam Long userId){
//        BoardDto.Response boardDetail =  boardService.getBoard(userId);
//        return ResponseEntity.ok(boardDetail);
//    }


}
