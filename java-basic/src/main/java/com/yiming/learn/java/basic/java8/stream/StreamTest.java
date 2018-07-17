package com.yiming.learn.java.basic.java8.stream;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yiming on 2018/1/11.
 */

public class StreamTest {

    private List<Student> students;

    @Test
    public void test() {
        List<Integer> nums = Lists.newArrayList(1, null, 3, 4, null, 6);
        nums = nums.stream().filter(Objects::nonNull).collect(Collectors.toList());
        nums.forEach(System.out::println);
    }

    @Before
    public void setUp() {
        students = buildUsers();
    }

    @Test
    public void testGenerate() {
        Stream.generate(Math::random).limit(100).forEach(System.out::println);
    }

    @Test
    public void testMap() {
        System.out.println("获取全部姓名:");
        List<String> names = students.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println(names);

        System.out.println("构建map:");
        Map<Integer, Student> userMap = students.stream().collect(Collectors.toMap(Student::getId, o -> o));
        userMap.forEach((k, v) -> System.out.println(k + ", " + v.getName()));

        userMap = students.stream().collect(Collectors.toMap(Student::getId, Function.identity()));
        userMap.forEach((k, v) -> System.out.println(k + ", " + v.getName()));

        Map<Integer, Map<SportTypeEnum, List<Student>>> userMapByGrade
            = students.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors.groupingBy(u -> u.getHobby().getType())));
        userMapByGrade.forEach((k, v) -> System.out.println(k + "班: " + JSON.toJSONString(v)));

        Map<Integer, IntSummaryStatistics> userCountMap
            = students.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors.summarizingInt(Student::getAge)));
        userCountMap.forEach((k, v) -> System.out.println(k + "班平均年龄 - " + String.format("%.1f岁", v.getAverage())));
        userCountMap.forEach((k, v) -> System.out.println(k + "班人数 - " + String.format("%d人", v.getCount())));
        userCountMap.forEach((k, v) -> System.out.println(k + "班最小年龄为 - " + String.format("%d岁", v.getMin())));
        userCountMap.forEach((k, v) -> System.out.println(k + "班最大年龄为 - " + String.format("%d岁", v.getMax())));

    }

    @Test
    public void testFilter() {
        System.out.println("爱好为球类运动的同学:");
        students.stream().filter(o -> SportTypeEnum.BALL_GAME.equals(o.getHobby().getType())).forEach(System.out::println);

        System.out.println("爱球类的女生:");
        students.stream().filter(o -> o.getSex().equals(SEXEnum.GIRL)
            && SportTypeEnum.BALL_GAME.equals(o.getHobby().getType()))
            .forEach(System.out::println);

        System.out.println("爱田径运动的姓\"JOBS\"女生:");
        students.stream()
            .filter(o -> o.getSex().equals(SEXEnum.GIRL)
                && SportTypeEnum.ATHLETICS.equals(o.getHobby().getType())
                && o.getName().endsWith("JOBS"))
            .forEach(System.out::println);

        //收集结果集
        List<Student> ballGameStudent = students.stream().filter(o -> SportTypeEnum.BALL_GAME.equals(o.getHobby().getType()))
            .collect(Collectors.toList());
        System.out.println(ballGameStudent);
    }

    @Test
    public void testDistinct() {
        System.out.println("去重:");
        Student student1 = Student.builder().id(1).name("TOM.F").sex(SEXEnum.BOY)
            .hobby(Hobby.builder().type(SportTypeEnum.BALL_GAME).name("篮球").build())
            .build();
        students.add(student1);
        students = students.stream().distinct().collect(Collectors.toList());
        System.out.println(students.size());
    }

    @Test
    public void testReduce() {
        System.out.println("求和:");
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        System.out.println(list.stream().reduce((o1, o2) -> o1 + o2).get());
    }

    private List<Student> buildUsers() {
        Student student1 = Student.builder().id(1).grade(1).age(10).name("TOM.F").sex(SEXEnum.BOY)
            .hobby(Hobby.builder().type(SportTypeEnum.BALL_GAME).name("篮球").build()).build();
        Student student2 = Student.builder().id(2).grade(2).age(18).name("B.M").sex(SEXEnum.BOY)
            .hobby(Hobby.builder().type(SportTypeEnum.BALL_GAME).name("足球").build()).build();
        Student student3 = Student.builder().id(3).grade(1).age(19).name("C.F").sex(SEXEnum.GIRL)
            .hobby(Hobby.builder().type(SportTypeEnum.BALL_GAME).name("台球").build()).build();
        Student student4 = Student.builder().id(4).grade(2).age(22).name("S.JOBS").sex(SEXEnum.GIRL)
            .hobby(Hobby.builder().type(SportTypeEnum.ATHLETICS).name("跑步").build()).build();
        Student student5 = Student.builder().id(5).grade(1).age(17).name("E.T").sex(SEXEnum.BOY)
            .hobby(Hobby.builder().type(SportTypeEnum.ATHLETICS).name("跳高").build()).build();
        Student student6 = Student.builder().id(6).grade(2).age(22).name("F.JOBS").sex(SEXEnum.UNKNOWN)
            .hobby(Hobby.builder().type(SportTypeEnum.ATHLETICS).name("跑步").build()).build();

        return Lists.newArrayList(student1, student2, student3, student4, student5, student6);
    }

    @AllArgsConstructor
    enum SEXEnum {
        UNKNOWN(0, "未知"),
        BOY(1, "男生"),
        GIRL(2, "女生");

        private static Map<Integer, SEXEnum> map =
            Arrays.stream(SEXEnum.values()).collect(Collectors.toMap(SEXEnum::getCode, sexEnum -> sexEnum));
        @Getter
        private Integer code;
        @Getter
        private String sex;

        public static SEXEnum getEnumByCode(Integer code) {
            return Optional.ofNullable(map.get(code)).orElse(SEXEnum.UNKNOWN);
        }
    }

    @AllArgsConstructor
    enum SportTypeEnum {
        OTHERS(0, "其他"),
        BALL_GAME(1, "球类"),
        ATHLETICS(2, "田径");

        private static Map<Integer, SportTypeEnum> map =
            Arrays.stream(SportTypeEnum.values()).collect(Collectors.toMap(SportTypeEnum::getCode, sportTypeEnum -> sportTypeEnum));
        @Getter
        private Integer code;
        @Getter
        private String sex;

        public static SportTypeEnum getEnumByCode(Integer code) {
            return Optional.ofNullable(map.get(code)).orElse(SportTypeEnum.OTHERS);
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Student {

        private Integer id;
        private Integer grade;
        private String name;
        private SEXEnum sex;
        private Integer age = 20;
        private Hobby hobby;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Hobby {

        private SportTypeEnum type;
        private String name;
    }


}
