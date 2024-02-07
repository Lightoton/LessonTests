package TaskiTest1;

public class Taski1 {
    public int add(int arg, int arg2) {
        return arg + arg2;
    }

    public static int subtract(int arg, int arg2) {
        return arg - arg2;
    }

    public static int multiply(int arg, int arg2) {
        if (arg == 0 || arg2 == 0) return 0;
        return arg * arg2;
    }

    public static double divide(int arg, int arg2) {
        if (arg2 == 0) throw new ArithmeticException();
        return arg / arg2;
    }

    public static double power(double base, int power) {
        if (power == 0) return 1;
        double result = 1.0;
        for (int i = 0; i < power; i++) {
            result *= base;
        }
        return result;
    }

    public static int factorial(int n) {
        if (n < 0) throw new ArithmeticException();
        if (n == 0 || n == 1) return 1;
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static double absolute(double arg) {
        if (arg < 0) return -arg;
        return arg;
    }

    public static double sqrt(double number) {
        if (number == 0) return 0;
        if (number == 1) return 1;
        double epsilon = 1e-15; // Точность
        double x = number; // Начальное приближение
        while (absolute(x - number / x) > epsilon * x) {
            x = (x + number / x) / 2;
        }
        return x;
    }

    public static double logarithm(double base, double number) {
        if (base <= 0 || base == 1 || number <= 0) {
            throw new IllegalArgumentException("Base and argument must be positive and base must not be 1");
        }

        // Находим логарифм
        double result = 0;
        while (number >= base) {
            number /= base;
            result++;
        }

        // Если number стал меньше базы, то приближаемся к решению
        double fraction = number - 1; // Остаток
        double power = 1; // Степень базы
        double term = fraction; // Текущий член ряда
        int sign = 1; // Знак члена ряда
        while (Math.abs(term) > 1e-10) { // Задаем точность вычисления
            power *= fraction; // Увеличиваем степень базы
            term = sign * power / (double) ++result; // Вычисляем следующий член ряда
            sign = -sign; // Меняем знак следующего члена
            result += term; // Добавляем член к результату
        }

        return result;
    }

    public static double sin(double connerRadian) {
        double result = 0;
        int sign = 1;
        int n = 1;
        for (int i = 0; i < 10; i++) {
            result += (sign * power(connerRadian, n)) / factorial(n);
            sign *= -1;
            n += 2;
        }
        return result;
    }
}
