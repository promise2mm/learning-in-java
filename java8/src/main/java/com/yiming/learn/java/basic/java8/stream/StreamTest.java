package com.yiming.learn.java.basic.java8.stream;

import com.google.common.collect.Lists;
import lombok.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yiming on 2018/1/11.
 */

public class StreamTest {

    private List<User> users;

    public static void main(String[] args) {
        //Lists是Guava中的一个工具类
//        List<Integer> nums = Lists.newArrayList(1, null, 3, 4, null, 6);
//        nums.stream().filter(num -> num != null).collect(nums );
//        nums.stream().forEach(System.out::println);
        Stream.generate(Math::random).limit(100).forEach(System.out::println);
        new StreamTest().testGenerate();
    }

    @Before
    public void setUp() {
        users = buildUsers();
    }

    @Test
    public void testGenerate() {
        Stream.generate(Math::random).limit(100).forEach(System.out::println);
    }

    @Test
    public void testMap() {
        System.out.println("获取全部姓名:");
        List<String> names = users.stream().map(User::getName).collect(Collectors.toList());
        System.out.println(names);

        System.out.println("构建map:");
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(User::getId, o -> o));
        userMap.forEach((k, v) -> System.out.println(k + ", " + v.getName()));

        userMap = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        userMap.forEach((k, v) -> System.out.println(k + ", " + v.getName()));

    }

    @Test
    public void testFilter() {
        System.out.println("爱好为球类运动的同学:");
        users.stream().filter(o -> SportTypeEnum.BALL_GAME.equals(o.getHobby().getType())).forEach(System.out::println);

        System.out.println("爱球类的女生:");
        users.stream().filter(o -> o.getSex().equals(SEXEnum.GIRL) && SportTypeEnum.BALL_GAME.equals(o.getHobby().getType())).forEach(System.out::println);

        System.out.println("爱田径运动的姓\"JOBS\"女生:");
        users.stream().filter(o -> o.getSex().equals(SEXEnum.GIRL) && SportTypeEnum.ATHLETICS.equals(o.getHobby().getType()) && o.getName().endsWith("JOBS")).forEach(System.out::println);

        //收集结果集
        List<User> ballGameUser = users.stream().filter(o -> SportTypeEnum.BALL_GAME.equals(o.getHobby().getType())).collect(Collectors.toList());
        System.out.println(ballGameUser);
    }

    @Test
    public void testDistinct() {
        System.out.println("去重:");
        User user1 = User.builder().id(1).name("TOM.F").sex(SEXEnum.BOY).hobby(Hobby.builder().type(SportTypeEnum.BALL_GAME).name("篮球").build()).build();
        users.add(user1);
        users = users.stream().distinct().collect(Collectors.toList());
        System.out.println(users.size());
    }

    @Test
    public void testReduce() {
        System.out.println("求和:");
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        System.out.println(list.stream().reduce((o1, o2) -> o1 + o2).get());
    }

    private List<User> buildUsers() {
        User user1 = User.builder().id(1).name("TOM.F").sex(SEXEnum.BOY).hobby(Hobby.builder().type(SportTypeEnum.BALL_GAME).name("篮球").build()).build();
        User user2 = User.builder().id(2).name("B.M").sex(SEXEnum.BOY).hobby(Hobby.builder().type(SportTypeEnum.BALL_GAME).name("足球").build()).build();
        User user3 = User.builder().id(3).name("C.F").sex(SEXEnum.GIRL).hobby(Hobby.builder().type(SportTypeEnum.BALL_GAME).name("台球").build()).build();
        User user4 = User.builder().id(4).name("S.JOBS").sex(SEXEnum.GIRL).hobby(Hobby.builder().type(SportTypeEnum.ATHLETICS).name("跑步").build()).build();
        User user5 = User.builder().id(5).name("E.T").sex(SEXEnum.BOY).hobby(Hobby.builder().type(SportTypeEnum.ATHLETICS).name("跳高").build()).build();
        User user6 = User.builder().id(6).name("F.JOBS").sex(SEXEnum.UNKNOWN).hobby(Hobby.builder().type(SportTypeEnum.ATHLETICS).name("跑步").build()).build();

        return Lists.newArrayList(user1, user2, user3, user4, user5, user6);
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
    static class User {
        private Integer id;
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
