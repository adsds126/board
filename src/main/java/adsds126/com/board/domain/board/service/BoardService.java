package adsds126.com.board.domain.board.service;

import adsds126.com.board.domain.board.dto.BoardDto;
import adsds126.com.board.domain.board.entity.Board;
import adsds126.com.board.domain.board.repository.BoardRepository;
import adsds126.com.board.domain.user.entity.User;
import adsds126.com.board.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserService userService;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserService userService) {
        this.boardRepository = boardRepository;
        this.userService = userService;
    }

    public Board createBoard(String userId, BoardDto.Post boardDto) {
        User user = userService.getUserById(userId);

        Board newBoard = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .user(user)
                .regDate(LocalDateTime.now())
                .uptDate(LocalDateTime.now())
                .build();

        return boardRepository.save(newBoard);
    }
    public Board updateBoard(BoardDto.Update boardDto) {
        Board existingBoard = boardRepository.findById(boardDto.getBoardReq())
                .orElseThrow(() -> new EntityNotFoundException("Board not found with ID: " + boardDto.getBoardReq()));

        existingBoard.setTitle(boardDto.getTitle());
        existingBoard.setContent(boardDto.getContent());
        existingBoard.setUptDate(LocalDateTime.now());
//        Board existingBoard = boardRepository.findByBoardId(boardDto.getBoardReq());
//
//        existingBoard = Board.builder()
//                .title(boardDto.getTitle())
//                .content(boardDto.getContent())
//                .uptDate(LocalDateTime.now())
//                .build();
        return boardRepository.save(existingBoard);
    }

    public void deleteBoard(Long boardSeq) {
        Board existingBoard = boardRepository.findById(boardSeq)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with ID: " + boardSeq));

        boardRepository.delete(existingBoard);
    }

//    public List<BoardDto.Response> getBoard(Long userSeq) {
//        List<BoardDto.Response> boards = boardRepository.findAllByUserSeq(userSeq);
//        return boards;
//    }
}
