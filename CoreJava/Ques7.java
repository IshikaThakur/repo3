import java.util.Scanner;

public class Ques7
{
 public static void main(String args[]) {
     Scanner sc = new Scanner(System.in);
     System.out.println("Enter your first name");
     String first = sc.nextLine();
     System.out.println("Enter your last name");
     String last = sc.nextLine();
     staticDemo sd= new staticDemo ();
     sd.showname(first, last);
 }}
 class staticDemo {
     static int age;
     static String firstname = " ";
     static String lastname = " ";

     static {
         age = 20;
     }


     static void showname(String first, String last) {
         firstname = first;
         lastname = last;
         System.out.println("Your first name is="+firstname);
         System.out.println("your last name is="+lastname);
         System.out.println("the age is="+age);


     }
 }

