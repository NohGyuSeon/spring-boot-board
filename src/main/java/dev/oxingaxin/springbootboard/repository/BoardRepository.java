package dev.oxingaxin.springbootboard.repository;

import dev.oxingaxin.springbootboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
