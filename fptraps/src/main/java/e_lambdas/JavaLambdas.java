package e_lambdas;

import com.google.common.math.DoubleMath;

import java.util.stream.IntStream;

public class JavaLambdas {

    public static void main(String[] args) {
        IntStream.range(0, 100)
                .filter(num -> DoubleMath.isMathematicalInteger(Math.sqrt(num)))
                .forEach(System.out::println);
    }
}
