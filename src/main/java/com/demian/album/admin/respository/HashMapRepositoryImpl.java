package com.demian.album.admin.respository;

import com.demian.album.admin.AdminRepository;
import com.demian.album.admin.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class HashMapRepositoryImpl implements AdminRepository {

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
    public Optional<Member> findByLoginedId(String loginedId) {
//        List<Member> all = findAll();
//        for(Member m : all) {
//            if(m.getLoginId().equals(loginedId)){
//                return Optional.of(m);
//            }
//        }
//        return Optional.empty();

        return findAll().stream()
                .filter(m->m.getLoginId().equals(loginedId))
                .findFirst();
    }

    @Override
    public void update(Long id, Member member) {
        Member targetMember = findById(id);
        targetMember.setName(member.getName());
        targetMember.setLoginId(member.getLoginId());
        targetMember.setPassword(member.getPassword());
        targetMember.setTelNo(member.getTelNo());
        targetMember.setIpCheck(member.isIpCheck());
        targetMember.setMemberType(member.getMemberType());
    }

    @Override
    public void clearDb() {
        db.clear();
    }

}
