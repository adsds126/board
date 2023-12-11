package adsds126.com.board.domain.user.controller;

import adsds126.com.board.common.ApiResponse;
import adsds126.com.board.domain.user.entity.User;
import adsds126.com.board.domain.user.service.UserService;
import adsds126.com.board.oauth.entity.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse getUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        User user = userService.getUser(userPrincipal.getUsername());
        return ApiResponse.success("user", user);
    }
}

