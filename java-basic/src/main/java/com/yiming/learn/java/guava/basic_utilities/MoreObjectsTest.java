package com.yiming.learn.java.guava.basic_utilities;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by 一鸣 on 2019/1/17 16:06. Description:
 *
 * @author yiming
 */
@Slf4j
public class MoreObjectsTest {

    @Test
    public void test() {
        String hello = MoreObjects.firstNonNull(null, "hello");
        log.info("{}", hello);

        ToStringHelper toStringHelper = MoreObjects.toStringHelper(new Employee("A", 100.0));
        toStringHelper.add("age", "27");
        log.info("{}", toStringHelper);
    }

    @Data
    @AllArgsConstructor
    class Employee {
        private String name;
        private Double salary;
    }

}
