package com.example.springsample.login.domain.repository.jdbc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.springsample.login.domain.model.User;

// ResultSetExtractor(複数件) を使用したMapping
@Repository("UserDaoJdbcImpl4")
public class UserDaoJdbcImpl4 extends UserDaoJdbcImpl {
    
    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<User> selectMany() {
        
        // M_USERテーブルのデータを全件取得するSQL
        String sql = "SELECT * FROM m_user";

        // ResultSetExtractorの生成
        UserResultSetExtractor extractor = new UserResultSetExtractor();

        // SQL実行
        return jdbc.query(sql, extractor);
    }

}
