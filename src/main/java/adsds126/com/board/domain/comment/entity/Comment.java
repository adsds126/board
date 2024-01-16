package adsds126.com.board.domain.comment.entity;

import adsds126.com.board.domain.board.entity.Board;
import adsds126.com.board.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @Column(name = "COMMENT_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentSeq;

    @NotNull
    @Size(max = 255)
    @Column(name = "TEXT", length = 100)
    private String text;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "board_fk")
    private Board board;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_fk")
    private User user;

    @CreatedDate
    private LocalDateTime regDate; // 등록 날짜

    @LastModifiedDate
    private LocalDateTime uptDate; // 수정 날짜

}