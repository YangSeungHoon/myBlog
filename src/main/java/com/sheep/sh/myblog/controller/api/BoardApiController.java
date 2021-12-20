package com.sheep.sh.myblog.controller.api;

import com.sheep.sh.myblog.config.auth.PrincipalDetail;
import com.sheep.sh.myblog.dto.ResponseDto;
import com.sheep.sh.myblog.model.Board;
import com.sheep.sh.myblog.model.User;
import com.sheep.sh.myblog.service.BoardService;
import com.sheep.sh.myblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board,
                                     @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.saveBoard(board,principal.getUser());
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }


}
