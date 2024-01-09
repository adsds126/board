package adsds126.com.board.domain.board.entity;

import adsds126.com.board.domain.comment.entity.Comment;
import adsds126.com.board.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOARDS")
public class  Board {

    @JsonIgnore
    @Id
    @Column(name = "BOARD_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardSeq;

    @Column(name = "TITLE", length = 100)
    @NotNull
    @Size(max = 100)
    private String title;

    @Column(name = "CONTENT", length = 1000, unique = true)
    @NotNull
    @Size(max = 512)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "USER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @CreatedDate
    private LocalDateTime regDate;     //등록 날짜

    @LastModifiedDate
    private LocalDateTime uptDate;     //수정 날짜

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private final List<Comment> comments = new ArrayList<>();
}
