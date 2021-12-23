package com.sheep.sh.myblog.service;

import com.sheep.sh.myblog.model.Board;
import com.sheep.sh.myblog.model.Reply;
import com.sheep.sh.myblog.model.User;
import com.sheep.sh.myblog.repository.BoardRepository;
import com.sheep.sh.myblog.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;


    @Transactional
    public void saveBoard(Board board, User user) {

        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> getBoardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    public Board detailBoard(Long id) {

        return boardRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다."));
    }

    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateBoard(Long id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다."));

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    }

    @Transactional
    public void writeReply(User user, Long boardId, Reply reply) {

        Board board = boardRepository.findById(boardId).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 게시글 id를 찾을 수 없습니다."));
        reply.setUser(user);
        reply.setBoard(board);

        replyRepository.save(reply);
    }
}
