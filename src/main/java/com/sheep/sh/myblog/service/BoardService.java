package com.sheep.sh.myblog.service;

import com.sheep.sh.myblog.model.Board;
import com.sheep.sh.myblog.model.User;
import com.sheep.sh.myblog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;


    @Transactional
    public void saveBoard(Board board, User user) {

        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }


}
