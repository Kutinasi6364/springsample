package com.example.springsample.login.domain.model;

import lombok.Data;
import java.util.Date;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;



@Data
public class SignupForm {

    // 必須入力、メールアドレス形式
    @NotBlank(groups = ValidGroup1.class)
    @Email(groups = ValidGroup2.class)
    private String userId; // ユーザーID

    // 必須入力、Len4-100, 半角英数のみ
    @NotBlank(groups = ValidGroup1.class)
    @Length(min = 4, max = 100, groups = ValidGroup2.class)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup3.class)
    private String password; // パスワード

    // 必須入力
    @NotBlank(groups = ValidGroup1.class)
    private String userName; // ユーザー名

    // 必須入力
    @NotNull(groups = ValidGroup1.class)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday; // 誕生日

    // 値が20-100
    @Min(value = 20, groups = ValidGroup2.class)
    @Max(value = 100, groups = ValidGroup2.class)
    private int age; // 年齢

    // falseのみ可能
    @AssertFalse(groups = ValidGroup2.class)
    private boolean marriage; // 結婚ステータス
}
