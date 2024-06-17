package com.hello.board;

import com.hello.board.repository.BoardRepository;
import com.hello.board.repository.JdbcTemplateBoardRepository;
import com.hello.board.service.BoardService;
import com.hello.board.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    private DataSource dataSource;

    @Autowired
    public AppConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public BoardRepository boardRepository() {
        return new JdbcTemplateBoardRepository(dataSource);
    }

    @Bean
    public BoardService boardService() {
        return new BoardServiceImpl(boardRepository());
    }

}
