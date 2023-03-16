package com.ll.basic1.boundedContext.member;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public class Rq {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public void setCookie(String name, String value) {
        Cookie newCookie = new Cookie(name, value);
        resp.addCookie(newCookie);
    }

    public void setCookie(String name, long value) {
        setCookie(name, value + "");
    }

    public String getCookie(String name, String defaultValue) {
        if(req.getCookies() == null) return defaultValue;

        return Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals(name))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(defaultValue);
    }

    public long getCookieAsLong(String name, long defaultValue) {
        String value = getCookie(name, null);

        if(value == null) {
            return defaultValue;
        }

        try {
            return Long.parseLong(value);
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean removeCookie(String name) {
        if (req.getCookies() != null) {
            Cookie cookie = Arrays.stream(req.getCookies())
                    .filter(c -> c.getName().equals(name))
                    .findFirst()
                    .orElse(null);

            if(cookie != null) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);

                return true;
            }
        }

        return false;
    }

}
