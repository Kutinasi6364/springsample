package com.example.springsample;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.springsample.login.domain.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Test
    @WithMockUser
    public void ユーザーリスト画面のユーザー件数のテスト() throws Exception {
    
        // UserService の count メソッドの戻り値を10に設定
        when(service.count()).thenReturn(10);

        // ユーザー一覧画面のチェック
        mockMvc.perform(get("/userList"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("合計 : 10件"))); // 個々の文字列を探す(一致していればOK)

    }
}
