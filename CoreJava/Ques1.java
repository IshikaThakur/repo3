import java.util.Scanner;

public class Ques1 {
        public static void main (String args[]) {
            Scanner sc = new Scanner(System.in);
            String str,str1,str2;
            System.out.println("Enter the string");
            str=sc.nextLine();
            System.out.println("Enter the string to be replaced");
            str1=sc.nextLine();
            System.out.println("Enter the string with which u want to replace");
            str2=sc.nextLine();
            System.out.println( str. replaceAll(str1,str2));
        }


}


