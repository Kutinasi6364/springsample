package com.example.springsample.login.domain.service;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springsample.login.domain.model.User;
import com.example.springsample.login.domain.repository.UserDao;

@Transactional
@Service
public class UserService {
    
    @Autowired // インターフェース実装クラス注入宣言
    @Qualifier("UserDaoJdbcImpl") // Bean(実装クラス)を選択
    UserDao dao; // Beadn(実装クラスが注入される)

    // insert用メソッド
    public boolean insert(User user) {
        
        // insert実行
        int rowNumber = dao.insertOne(user);

        // 判定用変数
        boolean result = false;

        // rowNumberには登録した件数が返ってくる
        if (rowNumber > 0) {
            // insert成功
            result = true;
        }

        return result;
    }

    // カウント用メソッド
    public int count() {
        return dao.count();
    }

    // 全件取得用メソッド
    public List<User> selectMany() {
        // 全件取得
        return dao.selectMany();
    }

    // 1件取得用メソッド
    public User selectOne(String userId) {
        // selectOne実行
        return dao.selectOne(userId);
    }

    // 1件更新メソッド
    public boolean updateOne(User user) {
        
        // 1件更新
        int rowNumber = dao.updateOne(user);

        // 判定用変数
        boolean result = false;

        // rowNumberには更新した件数が返ってくる
        if (rowNumber > 0) {
            // update成功
            result = true;
        }

        return result;
    }

    // 1件削除メソッド
    public boolean deleteOne(String userId) {
    
        // 1件削除
        int rowNumber = dao.deleteOne(userId);

        // 判定用変数
        boolean result = false;

        if (rowNumber > 0) {
            // delete成功
            result = true;
        }

        return result;
    }


    // ユーザー一覧をCSV出力する
    public void userCsvOut() throws DataAccessException {
    
        // CSV出力
        dao.userCsvOut();
    }

    // サーバーに保存されているファイルを取得して, byte配列に変換する
    public byte[] getFile(String fileName) throws IOException {
    
        // ファイルシステム(デフォルト)の取得
        FileSystem fs = FileSystems.getDefault();


        // ファイル取得
        Path p = fs.getPath(fileName);


        // ファイルをbyte配列に変換
        byte[] bytes = Files.readAllBytes(p);

        return bytes;
    }
}