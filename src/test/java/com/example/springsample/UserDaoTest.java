package com.example.springsample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.example.springsample.login.domain.repository.UserDao;

// テスト用アノテーション
@SpringBootTest
@Transactional
public class UserDaoTest {
    
    @Autowired
    @Qualifier("UserDaoJdbcImpl")
    UserDao dao;

    // カウントメソッドのテスト1
    @Test
    public void countTest1() {
    
        // カウントメソッドの結果が2件であることをテスト
        assertEquals(dao.count(), 2);
    }

    // カウントメソッドのテスト2
    @Test
    @Sql("/testdata.sql")
    public void countTest2() {
    
        // カウントメソッドの結果が3件であることをテスト
        assertEquals(dao.count(), 3);
    }
}
