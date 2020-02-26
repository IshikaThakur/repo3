package Java8;


    import java.util.*;
    interface Increment
    {
        int calculate(int a);
    }
    public class Ques1b {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter  Integer value");
            int x = sc.nextInt();
            Increment obj = (a) -> a +1;
            System.out.println("Number after incrementing " + obj.calculate(x));
        }

    }

