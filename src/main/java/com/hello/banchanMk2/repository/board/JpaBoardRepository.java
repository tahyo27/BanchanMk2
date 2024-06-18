package com.hello.banchanMk2.repository.board;

import com.hello.banchanMk2.domain.board.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
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

    @Override
    @Transactional
    public void deleteBoard(Integer num) {
        Board board = em.find(Board.class, num);
        if(board != null) {
            log.info("deleteBoard board is not null" + board.toString());
            em.remove(board);
        } else {
            throw new EntityNotFoundException("Board with num : " + num + " not found");
        }
    }
}
