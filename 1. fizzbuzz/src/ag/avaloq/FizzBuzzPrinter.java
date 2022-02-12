package ag.avaloq;

import java.util.stream.IntStream;

public class FizzBuzzPrinter {

    public static void main(String[] args) {
        print();
    }

    private static void print() {
        IntStream.range(1, 100)
                .mapToObj(n -> isMultipleOfThree(n) ? "Fizz" :
                            isMultipleOfFive(n) ? "Buzz" :
                                    isMultipleOfThree(n) && isMultipleOfFive(n) ? "FizzBuzz" :
                                            String.valueOf(n))
                .forEach(System.out::println);
    }

    private static boolean isMultipleOfThree(int n) {
        return n % 3 == 0;
    }

    private static boolean isMultipleOfFive(int n) {
        return n % 5 == 0;
    }
}
