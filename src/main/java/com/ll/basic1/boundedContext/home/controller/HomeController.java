package com.ll.basic1.boundedContext.home.controller;

import com.ll.basic1.boundedContext.member.entity.Member;
import com.ll.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {
    private int index;
    private final List<Person> people;
    @Autowired
    private MemberService memberService;

    public HomeController() {
        index = 0;
        people = new ArrayList<>();
    }

    @GetMapping("/home/main")
    @ResponseBody
    public String showMain() {
        return "안녕하세요.";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showMain2() {
        return "반갑습니다.";
    }

    @GetMapping("/home/main3")
    @ResponseBody
    public String showMain3() {
        return "즐거웠습니다.";
    }

    @GetMapping("/home/increase")
    @ResponseBody
    public int showIncrease() {
        return index++;
    }

    @GetMapping("/home/plus")
    @ResponseBody
    public int showPlus(@RequestParam(defaultValue = "0") int a, @RequestParam(defaultValue = "0") int b) {
        return a + b;
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(@RequestParam String name, @RequestParam int age) {
        Person person = new Person(name, age);

        people.add(person);

        System.out.println(person); // 객체가 생성되었는지 확인차 콘솔창에 출력

        return person.getId() + "번 사람이 추가되었습니다.";
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePerson(int id) {
        boolean removed = people.removeIf(person -> person.getId() == id);

        if(!removed) {
            return id + "번 사람이 존재하지 않습니다.";
        }
        return id + "번 사람이 삭제되었습니다.";
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String modifyPerson(int id, String name, int age) {
        Person found = people.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if(found == null) {
            return id + "번 사람이 존재하지 않습니다.";
        }

        found.setName(name);
        found.setAge(age);

        return id + "번 사람이 수정되었습니다.";
    }

    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> showPeople() {
        return people;
    }

    @GetMapping("/home/cookie/increase")
    @ResponseBody
    public int showCookieIncrease(HttpServletRequest req, HttpServletResponse resp) {
        int count = 0;

        Cookie[] cookies = req.getCookies();

        if(cookies != null) {
            count = Arrays.stream(cookies)
                    .filter(e -> e.getName().equals("count"))
                    .map(Cookie::getValue)
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }

        resp.addCookie(new Cookie("count", count + 1 + ""));

        return count;
    }

    @GetMapping("/home/user1")
    @ResponseBody
    public Member showUser1() {
        return memberService.findByUsername("user1");
    }

}
