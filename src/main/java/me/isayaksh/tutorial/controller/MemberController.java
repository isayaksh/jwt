package me.isayaksh.tutorial.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.isayaksh.tutorial.domain.Member;
import me.isayaksh.tutorial.jwt.TokenInfo;
import me.isayaksh.tutorial.service.MemberService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;


    @PostMapping("/join")
    public String join(@RequestBody MemberLoginRequestDto memberLoginRequestDto){
        memberService.join(memberLoginRequestDto.getUsername(), memberLoginRequestDto.getPassword());
        return "join success";
    }

    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginRequestDto dto) {
        return memberService.login(dto.getUsername(), dto.getPassword());
    }

    @GetMapping
    public List<Member> members(){
        return memberService.findAll();
    }
}
