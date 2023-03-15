package com.ll.basic1.boundedContext.Member.service;

import com.ll.basic1.base.RsData;
import com.ll.basic1.boundedContext.Member.controller.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberService {
    private List<Member> members;

    public MemberService() {
        members = new ArrayList<>();
        members.add(new Member("user1", "1234"));
    }

    public RsData tryLogin(String username, String password) {
        for(Member member : members) {
            if(!member.getPassword().equals(password)) {
                return RsData.of("F-1", "비밀번호가 일치하지 않습니다.");
            }
            else if(!member.getUsername().equals(username)) {
                return RsData.of("F-2", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
            }
        }
        return RsData.of("S-1", "%s 님 환영합니다.".formatted(username));
    }
}
