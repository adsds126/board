package adsds126.com.board.domain.board.controller;

import adsds126.com.board.common.ApiResponse;
import adsds126.com.board.domain.board.dto.BoardDto;
import adsds126.com.board.domain.board.entity.Board;
//import adsds126.com.board.domain.board.mapper.BoardMapper;
import adsds126.com.board.domain.board.repository.BoardRepository;
import adsds126.com.board.domain.board.service.BoardService;
import adsds126.com.board.oauth.entity.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;
    //private final BoardMapper boardMapper;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Board> createBoard(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody @Valid BoardDto.Post boardDto) {
        String userId = userPrincipal.getUserId();
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
    @GetMapping("/{boardSeq}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getBoardBySeq(@PathVariable Long boardSeq){
        Board board = boardService.findByBoardSeq(boardSeq);

        if (board == null) {
            // 게시글이 존재하지 않을 경우 처리
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.failBoard());
        }

        BoardDto.Response boardDetail = boardService.convertToResponseDto(board);

        return ResponseEntity.ok().body(ApiResponse.success("data", boardDetail));
    }
//    @GetMapping
//    @PreAuthorize("isAuthenticated()")
//    public ResponseEntity<?> getBoard(@AuthenticationPrincipal UserPrincipal userPrincipal){
//        Board board = boardService.getBoard(userPrincipal.getUserId());
//        BoardDto.Response boardDetail =  boardMapper.boardToBoardDtoResponse(board);
//        return ResponseEntity.ok().body(ApiResponse.success("data", boardDetail));
//    }
    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAllBoards(@AuthenticationPrincipal UserPrincipal userPrincipal){
        List<BoardDto.Response> response = boardService.getAllBoards(userPrincipal.getUserId());
        return ResponseEntity.ok().body(ApiResponse.success("data",response));
    }
}
