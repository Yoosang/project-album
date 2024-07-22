package com.demian.album.admin;

import com.demian.album.admin.domain.Member;

import java.util.List;

public interface AdminRepository {

    public Member insert(Member member);

    public Member findById(Long id);

    public List<Member> findAll();

    public void update(Long id, Member member);

    public void clearDb();

}
