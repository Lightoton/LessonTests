package TaskiTest1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Taski1Test {
Taski1 taski1 = new Taski1();
    @Test
    void additionalTest() {
        int actual = taski1.add(8, 12);
        Assertions.assertEquals(20, actual);
    }

    @Test
    void subtractTest() {
        int actual = Taski1.subtract(12, 8);
        Assertions.assertEquals(4, actual);
    }

    @Test
    void multiplyTest() {
        int actual = Taski1.multiply(2, 6);
        Assertions.assertEquals(12, actual);
    }

    @Test
    void multiplyWithZeroTest() {
        int actual = Taski1.multiply(2, 0);
        Assertions.assertEquals(0, actual);
    }

    @Test
    void dividePositiveTest() {
        double actual = Taski1.divide(12, 2);
        Assertions.assertEquals(6, actual);
    }

    @Test
    void divideExceptionTest() {
        Assertions.assertThrows(ArithmeticException.class, () -> Taski1.divide(12, 0));
    }

    @Test
    void powerTest() {
        double actual = Taski1.power(2, 3);
        Assertions.assertEquals(8, actual);
    }
    @Test
    void powerWithZeroTest() {
        double actual = Taski1.power(2, 0);
        Assertions.assertEquals(1, actual);
    }


    @Test
    void factorialTest() {
        int actual = Taski1.factorial(4);
        Assertions.assertEquals(24, actual);
    }
    @Test
    void factorialWithZeroAndOneParamTest() {
        Assertions.assertEquals(1,Taski1.factorial(0));
        Assertions.assertEquals(1,Taski1.factorial(1));
    }
    @Test
    void factorialWithNegativeNumberTest() {
        Assertions.assertThrows(ArithmeticException.class,() -> Taski1.factorial(-5));
    }


    @Test
    void absoluteTest() {
        Assertions.assertEquals(5,Taski1.absolute(-5));
        Assertions.assertEquals(10,Taski1.absolute(10));
        Assertions.assertEquals(0,Taski1.absolute(0));
    }

    @Test
    void sqrtTest() {
        Assertions.assertEquals(4.0,Taski1.sqrt(16));
    }
    @Test
    void sqrtTestWithNullAndOneTest() {
        Assertions.assertEquals(1.0,Taski1.sqrt(1));
        Assertions.assertEquals(0.0,Taski1.sqrt(0));
    }

    @Test
    void logarithmTest() {
        Assertions.assertEquals(2,Taski1.logarithm(10,100));
    }
    @Test
    void logarithmWithWithZeroAndNegativeParamAndOneBaseTest() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> Taski1.logarithm(0,0));
        Assertions.assertThrows(IllegalArgumentException.class,() -> Taski1.logarithm(-5,25));
        Assertions.assertThrows(IllegalArgumentException.class,() -> Taski1.logarithm(1,25));
    }

    @Test
    void sin() {
        Assertions.assertEquals(0.997,Taski1.sin(1.5),0.001);
    }
}