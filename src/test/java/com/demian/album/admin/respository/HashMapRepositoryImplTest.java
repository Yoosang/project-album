package com.demian.album.admin.respository;

import com.demian.album.admin.AdminRepositoy;
import com.demian.album.admin.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class HashMapRepositoryImplTest {

    AdminRepositoy repository = new HashMapRepositoryImpl();

    @AfterEach
    void afterEach(){
        repository.clearDb();
    }

    @Test
    void insert(){
        //given
        Member member = new Member();
        member.setName("test1");
        member.setPassword("1234");

        //when
        Member insertedMember = repository.insert(member);

        //then
        Member findMember = repository.findById(member.getId());
        assertThat(findMember).isEqualTo(insertedMember);
    }

    @Test
    void findAll() {
        //given
        Member m1 = new Member();
        m1.setName("test1");
        m1.setPassword("1234");
        repository.insert(m1);

        Member m2 = new Member();
        m2.setName("test2");
        m2.setPassword("abcd");
        repository.insert(m2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(m1, m2);
    }

    @Test
    void update() {
        //given
        Member origin = new Member();
        origin.setName("test1");
        origin.setPassword("1234");
        Member inserted = repository.insert(origin);
        Long id = origin.getId();

        //when
        Member modified = new Member();
        modified.setName("test2");
        modified.setPassword("abcd");
        repository.update(id, modified);

        //then
        Member updatedMember = repository.findById(id);
        assertThat(updatedMember.getName()).isEqualTo(modified.getName());
        assertThat(updatedMember.getPassword()).isEqualTo(modified.getPassword());
    }
}