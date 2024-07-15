package com.demian.album.admin;

public enum MemberType {
    ADMIN("관리자"), PARENTS("부모"), FAMILY("가족"), ETC("일반");

    private final String description;

    MemberType(String description){
        this.description = description;
    }
}
