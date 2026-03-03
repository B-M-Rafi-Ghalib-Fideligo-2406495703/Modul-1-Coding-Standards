package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {

    @InjectMocks
    CarRepository carRepository;

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
    void testCreateWithExistingId() {
        Car saved = carRepository.create(car);
        assertEquals("car-id-123", saved.getCarId());
        assertEquals("Toyota Camry", saved.getCarName());
    }

    @Test
    void testCreateWithNullId() {
        Car newCar = new Car();
        newCar.setCarName("Honda Civic");
        newCar.setCarColor("Blue");
        newCar.setCarQuantity(3);

        Car saved = carRepository.create(newCar);
        assertNotNull(saved.getCarId());
        assertEquals("Honda Civic", saved.getCarName());
    }

    @Test
    void testFindAllEmpty() {
        Iterator<Car> iterator = carRepository.findAll();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testFindAllWithCars() {
        carRepository.create(car);

        Car car2 = new Car();
        car2.setCarId("car-id-456");
        car2.setCarName("Honda Civic");
        car2.setCarColor("Blue");
        car2.setCarQuantity(3);
        carRepository.create(car2);

        Iterator<Car> iterator = carRepository.findAll();
        assertTrue(iterator.hasNext());
        Car first = iterator.next();
        assertEquals("car-id-123", first.getCarId());
        Car second = iterator.next();
        assertEquals("car-id-456", second.getCarId());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testFindByIdFound() {
        carRepository.create(car);
        Car found = carRepository.findById("car-id-123");
        assertNotNull(found);
        assertEquals("Toyota Camry", found.getCarName());
    }

    @Test
    void testFindByIdNotFound() {
        carRepository.create(car);
        Car found = carRepository.findById("non-existent-id");
        assertNull(found);
    }

    @Test
    void testFindByIdWithMultipleCars() {
        carRepository.create(car);

        Car car2 = new Car();
        car2.setCarId("car-id-456");
        car2.setCarName("BMW X5");
        car2.setCarColor("Black");
        car2.setCarQuantity(2);
        carRepository.create(car2);

        Car found = carRepository.findById("car-id-456");
        assertNotNull(found);
        assertEquals("BMW X5", found.getCarName());
    }

    @Test
    void testUpdateSuccess() {
        carRepository.create(car);

        Car updated = carRepository.update("car-id-123", new Car());
        // need to set fields on updated object
        Car patch = new Car();
        patch.setCarName("Updated Camry");
        patch.setCarColor("White");
        patch.setCarQuantity(10);

        Car result = carRepository.update("car-id-123", patch);
        assertNotNull(result);
        assertEquals("Updated Camry", result.getCarName());
        assertEquals("White", result.getCarColor());
        assertEquals(10, result.getCarQuantity());
    }

    @Test
    void testUpdateNotFound() {
        Car patch = new Car();
        patch.setCarName("Ghost Car");

        Car result = carRepository.update("non-existent-id", patch);
        assertNull(result);
    }

    @Test
    void testUpdateWithMultipleCars() {
        carRepository.create(car);

        Car car2 = new Car();
        car2.setCarId("car-id-456");
        car2.setCarName("Honda Civic");
        car2.setCarColor("Blue");
        car2.setCarQuantity(3);
        carRepository.create(car2);

        Car patch = new Car();
        patch.setCarName("Honda Civic Updated");
        patch.setCarColor("Green");
        patch.setCarQuantity(99);

        Car result = carRepository.update("car-id-456", patch);
        assertNotNull(result);
        assertEquals("Honda Civic Updated", result.getCarName());
        assertEquals("Green", result.getCarColor());
        assertEquals(99, result.getCarQuantity());
    }

    @Test
    void testDeleteExisting() {
        carRepository.create(car);
        carRepository.delete("car-id-123");

        Car found = carRepository.findById("car-id-123");
        assertNull(found);
    }

    @Test
    void testDeleteNonExistent() {
        carRepository.create(car);
        carRepository.delete("non-existent-id");

        // original car should still be there
        Car found = carRepository.findById("car-id-123");
        assertNotNull(found);
    }
}
