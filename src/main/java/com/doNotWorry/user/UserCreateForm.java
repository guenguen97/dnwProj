package com.doNotWorry.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {



    @NotEmpty(message = "ID는 필수항목입니다.")
    private String loginID;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;

//    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
//    private String password2;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    private String email;

//
//    @NotEmpty(message = "닉네임은 필수항목입니다.")
//    private String nickname;

    @NotEmpty(message = "닉네임은 필수항목입니다.")
    private String nickName;




}
