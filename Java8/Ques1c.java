package Java8;

import java.util.Scanner;

interface Concat
{
    String calculate(String a,String b);
}
public class Ques1c {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the first string ");
        String c=sc.nextLine();
        System.out.println("Enter the first string ");
        String d=sc.nextLine();
        Concat obj=(a,b)-> a+b;
        System.out.println("The concatenated string= " +obj.calculate(c,d));



    }
}
