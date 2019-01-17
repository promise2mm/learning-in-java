package com.yiming.learn.java.guava.basic_utilities;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by 一鸣 on 2019/1/17 15:15. Description:
 *
 * @author yiming
 */
@Slf4j
public class JavaOptional {

    private final Employee DEFAULT_EMPLOYEE = new Employee("", 0.0);
    List<Employee> employees = Lists.newArrayList(
        new Employee("A", 1000.0),
        null,
        new Employee("C", 2000.0));


    @Test
    public void testOptional() {
        String str = null;

        //log.info("{}", Optional.of(str).orElse("hello")); // Fast fail 空指针异常
        log.info("{}", Optional.ofNullable(str).orElse("hello"));

        str = "world";
        log.info("{}", Optional.ofNullable(str).orElse("hello"));

    }

    @Test
    public void testCalculateSalary() {
        // 这样会抛出NPE
        // log.info("total salary: {}", employees.stream().mapToDouble(Employee::getSalary).sum());
        log.info("total salary: {}",
            employees.stream().filter(Objects::nonNull).mapToDouble(Employee::getSalary).sum());
        // 若使用Optional
        double sum = employees.stream().mapToDouble(
            x -> Optional.ofNullable(x).orElse(DEFAULT_EMPLOYEE).getSalary())
            .sum();
        log.info("total salary: {}", sum);

    }

    @Data
    @AllArgsConstructor
    private class Employee {

        private String name;

        private Double salary;

    }

}
