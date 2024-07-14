package com.demian.album.admin;

import com.demian.album.admin.domain.Member;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/members")
@RequiredArgsConstructor
public class AdminController {
    private final AdminRepositoy adminRepositoy;

    @GetMapping
    public String members(Model model){
        List<Member> members = adminRepositoy.findAll();
        log.info("Members = {}", members.toString());
        model.addAttribute("members", members);
        return "admin/members";
    }

    @GetMapping("/{memberSeq}")
    public String member(@PathVariable long memberSeq, Model model){
        Member member = adminRepositoy.findById(memberSeq);
        model.addAttribute("member", member);
        return "admin/member";
    }

    @GetMapping("/add")
    public String addMemberView() {
        return "admin/addMember";
    }

    @PostMapping("/add")
    public String addMember(Member member, RedirectAttributes redirectAttributes) {
        adminRepositoy.insert(member);
        redirectAttributes.addAttribute("memberSeq", member.getMemberSeq());
        redirectAttributes.addAttribute("isSaved", true);
        return "redirect:/admin/members/{memberSeq}";
    }

    @GetMapping("/{memberSeq}/edit")
    public String editMemberView(@PathVariable Long memberSeq, Model model) {
        Member member = adminRepositoy.findById(memberSeq);
        model.addAttribute("member", member);
        return "admin/editMember";
    }

    @PostMapping("/{memberSeq}/edit")
    public String editMember(@PathVariable Long memberSeq, Member member) {
        adminRepositoy.update(memberSeq, member);
        return "redirect:/admin/members/{memberSeq}";
    }

    @PostConstruct
    public void init() {
        adminRepositoy.insert(new Member("testA", "테스트1", "1234"));
        adminRepositoy.insert(new Member("testB", "테스트2", "5678"));
    }

}
