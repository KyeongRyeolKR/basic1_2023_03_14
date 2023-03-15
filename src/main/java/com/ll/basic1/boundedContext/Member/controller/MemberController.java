package com.ll.basic1.boundedContext.Member.controller;

import com.ll.basic1.base.RsData;
import com.ll.basic1.boundedContext.Member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
    private MemberService memberService;

    public MemberController() {
        memberService = new MemberService();
    }

    @GetMapping("/member/login")
    @ResponseBody
    public RsData login(String username, String password) {
        return memberService.tryLogin(username, password);
    }
}
