package com.hello.banchanMk2;

import com.hello.banchanMk2.repository.board.BoardRepository;
import com.hello.banchanMk2.repository.board.JdbcTemplateBoardRepository;
import com.hello.banchanMk2.repository.board.JpaBoardRepository;
import com.hello.banchanMk2.service.board.BoardService;
import com.hello.banchanMk2.service.board.BoardServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

  /*  private DataSource dataSource;

    public AppConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/
    @PersistenceContext
    private EntityManager em;

    @Autowired
    public AppConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public BoardRepository boardRepository() {

        //return new JdbcTemplateBoardRepository(dataSource);
        return new JpaBoardRepository(em);
    }

    @Bean
    public BoardService boardService() {
        return new BoardServiceImpl(boardRepository());
    }

}
