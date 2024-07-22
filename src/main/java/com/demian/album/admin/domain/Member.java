package com.demian.album.admin.domain;

import com.demian.album.admin.MemberType;
import lombok.Data;

@Data
public class Member {

    private Long memberSeq;
    private String loginId;
    private String name;
    private String password;
    private String telno;

    private boolean ipCheck;
    private MemberType memberType;

    public Member() {
    }

    public Member(String loginId, String name, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }
}
