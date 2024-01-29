package com.example.springsample;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login // ログイン処理(login.html → LoginController.java → SecurityConfig.java)
                .loginProcessingUrl("/login") // ログイン処理のパス
                .loginPage("/login") //ログインページ
                .failureUrl("/login?error") //ログイン失敗時の遷移先
                .usernameParameter("userId") //ログインページのユーザーID
                .passwordParameter("password") // ログインページのパスワード
                .defaultSuccessUrl("/home", true) // ログイン成功時の遷移先; 

        ).logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
        ).authorizeHttpRequests(authz -> authz
                .requestMatchers("/webjars/**").permitAll() // webjarsへアクセス許可
                .requestMatchers("/css/**").permitAll() // cssへアクセス許可
                .requestMatchers("/login").permitAll() // ログインページは直リンクOK
                .requestMatchers("/signup").permitAll() // ユーザー登録画面は直リンクOK
                .requestMatchers("/error/**").permitAll() // ユーザー登録画面は直リンクOK
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN") // adminのみ可
                .anyRequest().authenticated()
        );

        //http.csrf().disable();
        return http.build();
    }


    // ログイン処理時のユーザー情報をDBから取得する
    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        String userQuery =
                "select user_id,password,true from m_user where user_id = ?"; // ID, PW, 使用可否
        String authoritiesQuery =
                "select user_id,role from m_user where user_id = ?"; // ID, 権限
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery(userQuery);
        users.setAuthoritiesByUsernameQuery(authoritiesQuery); // 認証処理

        return users;
    }
}

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    
    //     // ログイン不要ページの設定
    //     http
    //         .authorizeHttpRequests(authz -> authz
    //             .requestMatchers("/webjars/**").permitAll() // webjarsへアクセス許可
    //             .requestMatchers("/css/**").permitAll() // cssへアクセス許可
    //             .requestMatchers("/login").permitAll() // ログインページは直リンクOK
    //             .requestMatchers("/signup").permitAll() // ユーザー登録画面は直リンクOK
    //             .requestMatchers("/error").permitAll() // エラー画面は直リンクOK
    //             .anyRequest().authenticated() // それ以外は直リンク禁止  
    //         ); 
            
    //     http
    //         .formLogin (login -> login
    //             .loginProcessingUrl("/login") // ログイン処理のパス
    //             .loginPage("/login") // ログインページの指定
    //             .failureUrl("/login") // ログイン失敗時の遷移先
    //             .usernameParameter("userId") // ログインページのユーザーID
    //             .passwordParameter("password") // ログインページのパスワード
    //             .defaultSuccessUrl("/home", true) // ログイン成功時の遷移先; 
    //             .permitAll()
    //         );
        
        // CSRF対策を無効に設定(一時的)
        // http.csrf().disable();

        // return http.build();
    // }

