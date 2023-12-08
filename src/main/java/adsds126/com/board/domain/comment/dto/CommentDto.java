package adsds126.com.board.domain.comment.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class CommentDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        @NotNull
        @Size(max = 255)
        private String text;

    }
    @Getter
    @AllArgsConstructor
    public static class Update {

        @NotNull
        @Size(max = 255)
        private String text;

    }
}

