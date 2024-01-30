package com.example.springsample;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import jakarta.servlet.http.HttpServletRequest;


// GETメソッドと指定されたURLの場合, CSRF対策のチェックをしない
public class RestMatcher implements RequestMatcher {
    
    // マッチャー
    private AntPathRequestMatcher matcher;

    // コンストラクタ
    public RestMatcher(String url) {
    
        super();
        matcher = new AntPathRequestMatcher(url);
    }

    // URLのマッチ条件
    @Override
    public boolean matches(HttpServletRequest request) {
    
        // GETメソッドならCSRFのチェックはしない
        if ("GET".equals(request.getMethod())) {
            return false;
        }

        // 特定のURLに該当する場合、CSRFチェックしない
        if (matcher.matches(request)) {
            return false;
        }

        return true;
    }
}
