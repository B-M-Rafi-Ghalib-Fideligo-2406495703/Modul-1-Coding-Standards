package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarId("car-id-123");
        car.setCarName("Toyota Camry");
        car.setCarColor("Red");
        car.setCarQuantity(5);
    }

    @Test
    void testGetCarId() {
        assertEquals("car-id-123", car.getCarId());
    }

    @Test
    void testGetCarName() {
        assertEquals("Toyota Camry", car.getCarName());
    }

    @Test
    void testGetCarColor() {
        assertEquals("Red", car.getCarColor());
    }

    @Test
    void testGetCarQuantity() {
        assertEquals(5, car.getCarQuantity());
    }

    @Test
    void testSetCarId() {
        car.setCarId("new-id");
        assertEquals("new-id", car.getCarId());
    }

    @Test
    void testSetCarName() {
        car.setCarName("Honda Civic");
        assertEquals("Honda Civic", car.getCarName());
    }

    @Test
    void testSetCarColor() {
        car.setCarColor("Blue");
        assertEquals("Blue", car.getCarColor());
    }

    @Test
    void testSetCarQuantity() {
        car.setCarQuantity(10);
        assertEquals(10, car.getCarQuantity());
    }
}
