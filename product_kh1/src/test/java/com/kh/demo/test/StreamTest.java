package com.kh.demo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
public class StreamTest {

    @Test
    void test1() {
        //배열
        String[] args = new String[]{"홍길동", "홍길순", "홍길남"};

        //일반 for문
        for (int i = 0; i < args.length; i++) {
            log.info(args[i]);
        }

        log.info("--------------------------");

        //향상된 for문
        for (String ele : args) {
            log.info(ele);
        }

        log.info("--------------------------");

        //stream & lamda
        Arrays.stream(args).forEach(ele -> {
            log.info(ele);
        });
    }

    enum Gender {
        MAN, WOMAN
    }

    @Test
    void test2() {

        @Data
        @AllArgsConstructor
        class Person {
            String name;
            int age;
            Gender gender;
        }

        Person p1 = new Person("홍길동", 40, Gender.MAN);
        Person p2 = new Person("홍길서", 30, Gender.MAN);
        Person p3 = new Person("홍길남", 20, Gender.MAN);
        Person p4 = new Person("홍길녀", 10, Gender.WOMAN);

        //컬렉션
//        List<Person> persons = new ArrayList<>();
//        persons.add(p1);
//        persons.add(p2);
//        persons.add(p3);
//        persons.add(p4);
        List<Person> persons = Arrays.asList(p1, p2, p3, p4);

        persons.stream().forEach(person -> {
            log.info(person.toString());
        });

        List<Person> list = persons.stream()
                                   .filter(person -> person.gender == Gender.WOMAN)
                                   .collect(Collectors .toList());

        log.info("요소 개수 = {}", list.size());
    }

}