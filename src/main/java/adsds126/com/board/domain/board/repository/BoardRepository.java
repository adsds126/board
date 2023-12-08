package adsds126.com.board.domain.board.repository;

import adsds126.com.board.domain.board.dto.BoardDto;
import adsds126.com.board.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findByBoardSeq(Long boardSeq);
    //List<BoardDto.Response> findAllByUserSeq(Long userSeq);
}
