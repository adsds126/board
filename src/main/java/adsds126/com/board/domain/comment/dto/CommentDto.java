package adsds126.com.board.domain.comment.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class CommentDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {

        @NotNull
        @Size(max = 255)
        private String text;

        @NotNull
        private Long boardSeq;

    }
    @Getter
    @AllArgsConstructor
    public static class Update {

        @NotNull
        @Size(max = 255)
        private String text;

    }
}

