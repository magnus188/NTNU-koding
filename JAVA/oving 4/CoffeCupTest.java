package oving4.testing;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class CoffeCupTest {
    private boolean checkState(CoffeeCup cup, double capacity, double volume) {
        return cup.getCapacity() == capacity && cup.getCurrentVolume() == volume;
    }

    @Test
    @DisplayName("Constructor")
    public void testConstructor() {
        Assertions.assertTrue(checkState(new CoffeeCup(20.0, 10.0), 20.0, 10.0));
        Assertions.assertTrue(checkState(new CoffeeCup(25.0, 10.0), 25.0, 10.0));
        Assertions.assertTrue(checkState(new CoffeeCup(2.0, 1.0), 2.0, 1.0));
        Assertions.assertTrue(checkState(new CoffeeCup(8.0, 6.0), 8.0, 6.0));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CoffeeCup(0.0, 1.0);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CoffeeCup(1.0, -4.0);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CoffeeCup(1.0, 5.0);
        });

        // should not be able to create cup with int
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CoffeeCup(5, 4);
        });
    }

    @Test
    @DisplayName("Test getVolume()")
    public void testgetVolume() {
        Assertions.assertEquals(8.0, new CoffeeCup(10.0, 8.0).getCurrentVolume());
    }

    @Test
    @DisplayName("Test getCapacity()")
    public void testgetCapacity() {
        Assertions.assertEquals(12.0, new CoffeeCup(12.0, 10.0).getCapacity());
    }

    @Test
    @DisplayName("Test increaseCupSize()")
    public void testIncreaseCupSize() {
        CoffeeCup cup = new CoffeeCup(10.0, 8.0);
        cup.increaseCupSize(2.0);
        Assertions.assertTrue(checkState(cup, 12.0, 8.0));
    }

    @Test
    @DisplayName("Test drinkCoffee()")
    public void testDrinkCoffee() {
        CoffeeCup cup = new CoffeeCup(10.0, 8.0);
        cup.drinkCoffee(2.0);
        Assertions.assertTrue(checkState(cup, 10.0, 6.0));
    }

    @Test
    @DisplayName("Test fillCoffee()")
    public void testFillCoffee() {
        CoffeeCup cup = new CoffeeCup(10.0, 8.0);
        cup.fillCoffee(2.0);
        Assertions.assertTrue(checkState(cup, 10.0, 10.0));
    }

}
