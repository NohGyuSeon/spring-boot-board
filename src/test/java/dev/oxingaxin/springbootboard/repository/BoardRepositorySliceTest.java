package dev.oxingaxin.springbootboard.repository;

import static org.assertj.core.api.Assertions.assertThat;

import dev.oxingaxin.springbootboard.entity.Board;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoardRepositorySliceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void testBoard() {
        Board testBoard = new Board("test", "test code", "ngs");
        Board mergeBoard = entityManager.merge(testBoard);

        Board board = boardRepository.findById(mergeBoard.getId()).orElseThrow();
        assertThat(board).isEqualTo(mergeBoard);

        log.info("mergeBoard id={}", mergeBoard.getId());
    }
}
