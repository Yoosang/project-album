package com.demian.album.admin.respository;

import com.demian.album.admin.AdminRepositoy;
import com.demian.album.admin.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HashMapRepositoryImpl implements AdminRepositoy {

    private static final Map<Long, Member> db = new HashMap<>();
    private static long seq = 0L;

    @Override
    public Member insert(Member member) {
        member.setMemberSeq(++seq);
        db.put(member.getMemberSeq(), member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return db.get(id);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public void update(Long id, Member member) {
        Member targetMember = findById(id);
        targetMember.setName(member.getName());
        targetMember.setLoginId(member.getLoginId());
        targetMember.setPassword(member.getPassword());
        targetMember.setIpCheck(member.isIpCheck());
    }

    @Override
    public void clearDb() {
        db.clear();
    }

}
