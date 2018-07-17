package com.yiming.learn.java.basic.java8.lambda.function;

import java.util.function.Supplier;
import org.junit.Test;

/**
 * Created by yiming on 2018-07-17 16:10. Description:
 *
 * @author yiming
 */
public class SupplierTest {

    @Test
    public void test() {
        driverVehicle(Vehicle::new);
        driverVehicle(() -> new Vehicle());
        driverVehicle(Car::new);
    }

    private static void driverVehicle(Supplier<Vehicle> supplier) {
        supplier.get().drive();
    }

    class Vehicle {

        public void drive() {
            System.out.println("drive vehicle...");
        }
    }

    class Car extends Vehicle {

        @Override
        public void drive() {
            System.out.println("drive car...");
        }
    }

}
