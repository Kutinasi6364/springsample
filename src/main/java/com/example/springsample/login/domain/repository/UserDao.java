package com.example.springsample.login.domain.repository;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.example.springsample.login.domain.model.User;

public interface UserDao {
    // Userテーブルの件数を取得
    public int count() throws DataAccessException;

    // Userテーブルにデータを1件登録
    public int insertOne(User user) throws DataAccessException;

    // Userテーブルのデータを1件取得
    public User selectOne(String userId) throws DataAccessException;

    // Userテーブルの全データを取得
    public List<User> selectMany() throws DataAccessException;
 
    // Userテーブルを1件更新
    public int updateOne(User user) throws DataAccessException;

    // Userテーブルを1件削除
    public int deleteOne(String userId) throws DataAccessException;

    // SQL取得結果をサーバーCSVに保存する
    public void userCsvOut() throws DataAccessException;
}
