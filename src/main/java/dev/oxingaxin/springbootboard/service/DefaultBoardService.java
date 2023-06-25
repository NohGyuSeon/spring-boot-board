package dev.oxingaxin.springbootboard.service;

import dev.oxingaxin.springbootboard.dto.BoardCreateRequest;
import dev.oxingaxin.springbootboard.entity.Board;
import dev.oxingaxin.springbootboard.repository.BoardRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultBoardService implements BoardService {
    private final BoardRepository boardRepository;
    @Override
    public Long createBoard(BoardCreateRequest boardCreateRequest) {
        Board board = new Board();
        board.setTitle(boardCreateRequest.getTitle());
        board.setContent(boardCreateRequest.getContent());
        board.setWriter(boardCreateRequest.getWriter());
        return boardRepository.save(board).getId();
    }

    @Override
    public void updateBoard(Long boardId, Board updateParam) {
        Board board = this.getBoard(boardId);
        board.setTitle(updateParam.getTitle());
        board.setContent(updateParam.getContent());
        board.setWriter(updateParam.getWriter());
        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(NoSuchElementException::new);
        boardRepository.delete(board);
    }

    @Override
    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow();
    }
}
