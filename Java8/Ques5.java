package Java8;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Ques5{
    public static void main(String[] args) {
        //PREDICATE
        Predicate<Integer> predicate = (e) -> (e % 2 == 0);
        System.out.println(predicate.test(11));
        //CONSUMER
        Consumer consumer = e -> {
            System.out.println("welcome" + e);
        };

        consumer.accept("Ishika");
//SUPPLIER
        Supplier supplier = () ->
        {
            return "anything";
        };
        System.out.println(supplier.get());
        //FUNCTION
        Function<Integer, Integer> function = (e) -> e /= 2;
        function = function.andThen(e -> e * 2);
        System.out.println(function.apply(22));
    }
}