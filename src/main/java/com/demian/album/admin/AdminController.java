package com.demian.album.admin;

import com.demian.album.admin.domain.Member;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/members")
@RequiredArgsConstructor
public class AdminController {
    private final AdminRepositoy adminRepositoy;

    @GetMapping
    public String members(Model model){
        List<Member> members = adminRepositoy.findAll();
        model.addAttribute("members", members);
        return "admin/members";
    }

    @PostConstruct
    public void init() {
        adminRepositoy.insert(new Member("testA", "테스트1", "1234"));
        adminRepositoy.insert(new Member("testB", "테스트2", "5678"));
    }

}
