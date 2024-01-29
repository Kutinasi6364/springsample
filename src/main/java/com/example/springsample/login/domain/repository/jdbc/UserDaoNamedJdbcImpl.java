package com.example.springsample.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.springsample.login.domain.model.User;
import com.example.springsample.login.domain.repository.UserDao;

@Repository("UserDaoNamedJdbcImpl")
public class UserDaoNamedJdbcImpl implements UserDao {
    
    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    // Userテーブルの件数を取得
    public int count() {
    
        // SQL文
        String sql = "SELECT COUNT(*) FROM m_user";

        // パラメータ生成
        SqlParameterSource params = new MapSqlParameterSource();

        // 全件syつ臆してカウント
        return jdbc.queryForObject(sql, params, Integer.class);
    }

    
    // Userテーブルにデータを1件追加
    @Override
    public int insertOne(User user) {
    
        // SQL文
        String sql = "INSERT INTO m_user(user_id,"
                    + "password,"
                    + "user_name,"
                    + "birthday,"
                    + "age,"
                    + "marriage,"
                    + "role)"
                    + "VALUES(:userId,"
                    + ":password,"
                    + ":userName,"
                    + ":birthday,"
                    + ":age,"
                    + ":marriage,"
                    + ":role)";
        
        // パラメータ
        SqlParameterSource params = new MapSqlParameterSource()
            .addValue("userId", user.getUserId())
            .addValue("password", user.getPassword())
            .addValue("userName", user.getUserName())
            .addValue("birthday", user.getBirthday())
            .addValue("age", user.getAge())
            .addValue("marriage", user.isMarriage())
            .addValue("role", user.getRole());
        
        // SQL実行
        return jdbc.update(sql, params);
    }


    // Userテーブルのデータを1件取得
    @Override
    public User selectOne(String userId) {
    
        // SQL文
        String sql = "SELECT * FROM m_user WHERE user_id = :userId";

        // パラメータ
        SqlParameterSource params = new MapSqlParameterSource()
            .addValue("userId", userId);
        
        // SQL実行
        Map<String, Object> map = jdbc.queryForMap(sql, params);

        // 結果返却用のインスタンスを生成
        User user = new User();

        // 取得データをインスタンスにセットしていく
        user.setUserId((String)map.get("user_id")); // ユーザーID
        user.setPassword((String)map.get("password")); // パスワード
        user.setUserName((String)map.get("user_name")); // ユーザー名
        user.setBirthday((Date)map.get("birthday")); // 誕生日
        user.setAge((Integer)map.get("age")); // 年齢
        user.setMarriage((boolean)map.get("marriage")); // 結婚ステータス
        user.setRole((String)map.get("role")); // ロール

        return user;
    }


    // Userテーブルの全データ件取得
    @Override
    public List<User> selectMany() {
    
        // SQL文
        String sql = "SELECT * FROM m_user";

        // パラメータ
        SqlParameterSource params = new MapSqlParameterSource();
        
        // SQL実行
        List<Map<String, Object>> getList = jdbc.queryForList(sql, params);

        // 結果返却用のList
        List<User> userList = new ArrayList<>();

        // 取得データ分Loop
        for (Map<String, Object> map: getList) {

            // 結果返却用のインスタンスを生成
            User user = new User();
    
            // 取得データをインスタンスにセットしていく
            user.setUserId((String)map.get("user_id")); // ユーザーID
            user.setPassword((String)map.get("password")); // パスワード
            user.setUserName((String)map.get("user_name")); // ユーザー名
            user.setBirthday((Date)map.get("birthday")); // 誕生日
            user.setAge((Integer)map.get("age")); // 年齢
            user.setMarriage((boolean)map.get("marriage")); // 結婚ステータス
            user.setRole((String)map.get("role")); // ロール
    
            userList.add(user);
        }

        return userList;
    }


    // Userテーブルを1件更新
    @Override
    public int updateOne(User user) {

        // SQL文
        String sql = "UPDATE M_USER SET"
                    + "password = :password,"
                    + "user_name = :userName,"
                    + "birthday = :birthday,"
                    + "age = :age,"
                    + "marriage = :marriage"
                    + "WHERE user_id = :userId";
        
        // パラメータ
        SqlParameterSource params = new MapSqlParameterSource()
                                    .addValue("userId", user.getUserId())
                                    .addValue("password", user.getPassword())
                                    .addValue("userName", user.getUserName())
                                    .addValue("birthday", user.getBirthday())
                                    .addValue("age", user.getAge())
                                    .addValue("marriage", user.isMarriage())
                                    .addValue("role", user.getRole());

        // SQL実行
        return jdbc.update(sql, params);
    }

    // Userテーブルを1件削除
    @Override
    public int deleteOne(String userId) {

        // SQL文
        String sql = "DELETE FROM m_user WHERE user_id = :userId";

        // パラメータ
        SqlParameterSource params = new MapSqlParameterSource()
            .addValue("userId", userId);
        
        // SQL実行
        int rowNumber = jdbc.update(sql, params);

        return rowNumber;
    }

    // Userテーブルの全データをCSVに出力する
    @Override
    public void userCsvOut() {
        
        // M_USERテーブルのデータを全件取得するSQL
        String sql = "SELECT * FROM m_user";

        // ResultSetExtractorの生成
        UserRowCallbackHandler handler = new UserRowCallbackHandler();

        // SQL実行 & CSV出力
        jdbc.query(sql, handler);
    }

}
