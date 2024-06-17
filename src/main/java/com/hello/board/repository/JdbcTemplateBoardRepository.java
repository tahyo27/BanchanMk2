package com.hello.board.repository;

import com.hello.board.domain.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateBoardRepository implements BoardRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateBoardRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Board save(Board board) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("board").usingGeneratedKeyColumns("boardNum");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("writer", board.getWriter());
        parameters.put("title", board.getTitle());
        parameters.put("content", board.getContent());
        parameters.put("regDate", board.getRegDate());
        parameters.put("viewCount", board.getViewCount());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        board.setBoardNum(key.intValue());

        return board;
    }

    @Override
    public List<Board> findAll() {
        return jdbcTemplate.query("select * from board", boardRowMapper());
    }

    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();

            board.setBoardNum(rs.getInt("boardNum"));
            board.setWriter(rs.getString("writer"));
            board.setTitle(rs.getString("title"));
            board.setContent(rs.getString("content"));
            board.setRegDate(rs.getTimestamp("regDate"));
            board.setViewCount(rs.getInt("viewCount"));

            return board;
        };
    }


}//class
