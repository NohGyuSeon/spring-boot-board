package dev.oxingaxin.springbootboard.service;

import dev.oxingaxin.springbootboard.dto.BoardCreateRequest;
import dev.oxingaxin.springbootboard.entity.Board;

import java.util.List;

public interface BoardService {

    Long createBoard(BoardCreateRequest boardCreateRequest);

    void updateBoard(Long boardId, Board board);

    void deleteBoard(Long boardId);

    List<Board> getBoards();

    Board getBoard(Long boardId);
}
