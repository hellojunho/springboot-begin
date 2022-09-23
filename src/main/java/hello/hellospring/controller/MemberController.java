package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    //private final MemberService memberService = new MemberService();
    // new MemberService()로 하면, 다른 여러 컨트롤러들이 MemberService를
    // 가져다 쓸 수 있다는 문제점이 존재한다.

    private final MemberService memberService;

    // alt + shift + insert : 생성자 자동 생성
    @Autowired  // 스프링 컨테이너에서 MemberService를 가져옴
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        
        // 확인용 출력문
        System.out.println("\n\nmember = " + member.getName() + "\n\n");

        memberService.join(member);

        return "redirect:/";    // home 화면으로 보내버림
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
