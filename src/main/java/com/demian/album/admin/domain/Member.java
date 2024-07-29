package com.demian.album.admin.domain;

import com.demian.album.admin.MemberType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Member {

    private Long memberSeq;

    @NotEmpty(message = "아이디를 입력하세요")
    private String loginId;

    @NotEmpty(message = "이름을 입력하세요")
    private String name;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;

    private String telNo;

    private boolean ipCheck;

    @NotNull(message = "필수 선택값 입니다.")
    private MemberType memberType;

    public Member() {
    }

    public Member(String loginId, String name, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }
}
