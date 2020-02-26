package Java8;
import java.util.*;
interface Greater
{
    boolean calculate(int a,int b);
}
public class Ques1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter first Integer value");
        int x = sc.nextInt();
        System.out.println("Please enter second Integer value");
        int y = sc.nextInt();
        Greater obj = (a, b) -> a > b ? true : false;
        System.out.println("First number is greater than second number" +" "+ obj.calculate(x,y));
    }

}