package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarId("car-id-123");
        car.setCarName("Toyota Camry");
        car.setCarColor("Red");
        car.setCarQuantity(5);
    }

    @Test
    void testCreate() {
        Mockito.when(carRepository.create(car)).thenReturn(car);
        Car result = carService.create(car);
        assertNotNull(result);
        assertEquals("car-id-123", result.getCarId());
        Mockito.verify(carRepository).create(car);
    }

    @Test
    void testFindAll() {
        Car car2 = new Car();
        car2.setCarId("car-id-456");
        List<Car> carList = Arrays.asList(car, car2);
        Iterator<Car> iterator = carList.iterator();

        Mockito.when(carRepository.findAll()).thenReturn(iterator);

        List<Car> result = carService.findAll();
        assertEquals(2, result.size());
        assertEquals("car-id-123", result.get(0).getCarId());
        assertEquals("car-id-456", result.get(1).getCarId());
        Mockito.verify(carRepository).findAll();
    }

    @Test
    void testFindById() {
        Mockito.when(carRepository.findById("car-id-123")).thenReturn(car);
        Car result = carService.findById("car-id-123");
        assertNotNull(result);
        assertEquals("car-id-123", result.getCarId());
        Mockito.verify(carRepository).findById("car-id-123");
    }

    @Test
    void testFindByIdNotFound() {
        Mockito.when(carRepository.findById("non-existent")).thenReturn(null);
        Car result = carService.findById("non-existent");
        assertNull(result);
        Mockito.verify(carRepository).findById("non-existent");
    }

    @Test
    void testUpdate() {
        carService.update("car-id-123", car);
        Mockito.verify(carRepository).update("car-id-123", car);
    }

    @Test
    void testDeleteCarById() {
        carService.deleteCarById("car-id-123");
        Mockito.verify(carRepository).delete("car-id-123");
    }
}
