package com.yiming.learn.java.basic.java8.lambda.function;

import java.util.function.Consumer;
import java.util.function.Predicate;
import org.junit.Test;

/**
 * Created by yiming on 2018-07-17 15:23. description:
 *
 * https://sanaulla.info/2013/03/27/function-interface-a-functional-interface-in-the-java-util-function-package-in-java-8/
 *
 * @author yiming
 */
public class PredicateTest {

    private static Predicate<Student> specialPredicate = student -> "D".equals(student.lastName);

    @Test
    public void test() {
        Student student1 = new Student("Namy", "K", 9.5);
        student1 = updateStudentFee(student1,
            student -> student.grade > 8.5, student -> student.feeDiscount = 30.0);
        student1.printFee();

        Student student2 = new Student("Monkey Luffy", "D", 7.0);
        student2 = updateStudentFee(student2,
            student -> student.grade >= 8, student -> student.feeDiscount = 20.0);
        student2.printFee();

        Student student3 = new Student("Sanji", "JirMar", 7.0);
        student3 = updateStudentFee(student3,
            student -> student.grade >= 8, student -> student.feeDiscount = 20.0);
        student3.printFee();
    }

    private Student updateStudentFee(Student student,
        Predicate<Student> predicate, Consumer<Student> consumer) {
        if (predicate.or(specialPredicate).test(student)) {
            consumer.accept(student);
        }
        return student;
    }

    class Student {

        private String firstName;
        private String lastName;
        private Double grade;
        private Double feeDiscount = 0.0;
        private Double baseFee = 20000.0;

        Student(String firstName, String lastName, Double grade) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.grade = grade;
        }

        private void printFee() {
            Double newFee = baseFee - ((baseFee * feeDiscount) / 100);
            System.out.println("The fee of " + firstName + " after discount: " + newFee);
        }
    }

}
