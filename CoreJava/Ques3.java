import java.util.*;
public class Ques3 {
        public static void main(String[] args)
        {
            Scanner sc=new Scanner(System.in);
            String str;
            System.out.println("Enter the string");
            str=sc.nextLine();
            System.out.println("Enter the character to be counted");
            String ch=sc.nextLine();
            int count = str.length() - str.replace(ch,"").length();
            System.out.println(ch+"occurs" +" "+count);
        }
    }

