package com.example.springsample.login.domain.repository.jdbc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.example.springsample.login.domain.model.User;

// BeanPropertyRowMapper を使用した自動マッピング
@Repository("UserDaoJdbcImpl3")
public class UserDaoJdbcImpl3 extends UserDaoJdbcImpl {
    
    @Autowired
    private JdbcTemplate jdbc;

    // ユーザー1件取得
    public User selectOne(String userId) {
        
        // 1件取得用SQL
        String sql = "SELECT * FROM m_user WHERE user_id = ?";

        // RowMappingの作成
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);

        // SQL実行
        return jdbc.queryForObject(sql, rowMapper, userId);
    }

    // ユーザー全取得
    @Override
    public List<User> selectMany() {
        
        // M_Userテーブルのデータを全件取得するSQL
        String sql = "SELECT * FROM m_user";

        // RowMapperの生成
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);

        // SQL実行
        return jdbc.query(sql, rowMapper);
    }
}
