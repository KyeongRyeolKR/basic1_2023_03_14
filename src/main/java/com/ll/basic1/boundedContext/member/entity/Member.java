package com.ll.basic1.boundedContext.member.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Data
public class Member {
    private static long lastId;
    private long id;
    private String username;
    private String password;

    static {
        lastId = 0;
    }

    public Member(String username, String password) {
        this(++lastId, username, password);
    }
}
