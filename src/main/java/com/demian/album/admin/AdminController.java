package com.demian.album.admin;

import com.demian.album.admin.domain.Member;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/members")
@RequiredArgsConstructor
public class AdminController {
    private final AdminRepository adminRepository;

    @ModelAttribute("memberTypes")
    public MemberType[] memberTypes() {
        return MemberType.values();
    }

    @GetMapping
    public String members(Model model){
        List<Member> members = adminRepository.findAll();
        log.info("Members = {}", members.toString());
        model.addAttribute("members", members);
        return "admin/members";
    }

    @GetMapping("/{memberSeq}")
    public String member(@PathVariable long memberSeq, Model model){
        Member member = adminRepository.findById(memberSeq);
        model.addAttribute("member", member);
        return "admin/member";
    }

    @GetMapping("/add")
    public String addMemberView(Model model) {
        model.addAttribute("member", new Member());
        return "admin/addMember";
    }

    @PostMapping("/add")
    public String addMember(@Valid Member member, BindingResult result, RedirectAttributes redirectAttributes) {
        log.info("MemberType: {}", member.getMemberType());

        if(result.hasErrors()){
            return "admin/addMember";
        }

        adminRepository.insert(member);
        redirectAttributes.addAttribute("memberSeq", member.getMemberSeq());
        redirectAttributes.addAttribute("isSaved", true);
        return "redirect:/admin/members/{memberSeq}";
    }

    @GetMapping("/{memberSeq}/edit")
    public String editMemberView(@PathVariable Long memberSeq, Model model) {
        Member member = adminRepository.findById(memberSeq);
        model.addAttribute("member", member);
        return "admin/editMember";
    }

    @PostMapping("/{memberSeq}/edit")
    public String editMember(@PathVariable Long memberSeq, Member member) {
        adminRepository.update(memberSeq, member);
        return "redirect:/admin/members/{memberSeq}";
    }

    @PostConstruct
    public void init() {
        adminRepository.insert(new Member("testA", "테스트1", "1234"));
        adminRepository.insert(new Member("testB", "테스트2", "5678"));
    }

}
