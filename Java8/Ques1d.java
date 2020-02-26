package Java8;

import java.util.Scanner;

interface Upper
{
    String convertUpper(String a);
}
public class Ques1d {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the String");
        String x=sc.nextLine();
        Upper obj=(a)->x.toUpperCase();
        System.out.println("the string in uppercase is="+obj.convertUpper(x));
    }
}
