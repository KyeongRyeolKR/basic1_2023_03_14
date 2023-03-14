package com.ll.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private int index;
    private int lastId;
    private List<Person> people;

    public HomeController() {
        index = 0;
        lastId = 1;
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

    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> showPeople() {
        return people;
    }
}
