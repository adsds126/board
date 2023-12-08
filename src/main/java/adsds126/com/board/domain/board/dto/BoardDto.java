package adsds126.com.board.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class BoardDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Post {


        @NotNull
        @Size(max = 100)
        private String title;

        @NotNull
        @Size(max = 1000)
        private String content;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Update {
        @NotNull
        private Long boardReq;
        @NotNull
        @Size(max = 100)
        private String title;

        @NotNull
        @Size(max = 1000)
        private String content;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        @NotNull
        private Long boardReq;

        @NotNull
        @Size(max = 100)
        private String title;

        @NotNull
        @Size(max = 1000)
        private String content;

        private LocalDateTime regDate;
        private LocalDateTime upDate;
    }

}
