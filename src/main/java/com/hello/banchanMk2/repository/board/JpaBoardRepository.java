package com.hello.banchanMk2.repository.board;

import com.hello.banchanMk2.domain.board.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public List<Board> pagedFindAll(Pageable pageable) {
        log.info("pagedFindAll" + pageable.toString());
        String jpql = "select b from Board b order by boardNum desc";
        log.info("pageable.getoffset" + pageable.getOffset());
        return em.createQuery(jpql, Board.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteBoard(Integer num) {
        Board board = em.find(Board.class, num);
        if(board != null) {
            log.info("deleteBoard Board is not null" + board.toString());
            em.remove(board);
        } else {
            throw new EntityNotFoundException("Board with num : " + num + " not found");
        }
    }

    @Override
    public Page<Board> pageTest(Pageable pageable) {

        String jpql = "select b from Board b order by b.boardNum desc";
        List<Board> boards = em.createQuery(jpql, Board.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        // 전체 레코드 수 조회
        String countJpql = "select count(b) from Board b";
        Long total = em.createQuery(countJpql, Long.class).getSingleResult();

        return new PageImpl<>(boards, pageable, total);
    }

}
