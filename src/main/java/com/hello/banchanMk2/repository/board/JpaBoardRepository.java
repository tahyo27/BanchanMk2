package com.hello.banchanMk2.repository.board;

import com.hello.banchanMk2.domain.board.Board;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public class JpaBoardRepository implements BoardRepository {

    private final EntityManager em;

    public JpaBoardRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    @Transactional
    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    @Override
    public List<Board> findAll() {
        List<Board> board = em.createQuery("SELECT b from Board b", Board.class).getResultList();
        return board;
    }

    @Override
    public Optional<Board> findByBoardNum(Integer num) {
        Board board = em.find(Board.class, num);
        return Optional.ofNullable(board);
    }
}
