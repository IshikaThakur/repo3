import java.util.Scanner;

public class Ques4 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String str;
        int i,countLower=0,countUpper=0,countSpecial=0,countDigit=0,len;
 System.out.println("Enter the string");
 str=sc.nextLine();
 len=str.length();
 for(i=0;i<str.length();i++)
 {
char ch=str.charAt(i);
     if (ch>=97&&ch<=122)
     {
         countLower=countLower+1;
     }
     else if(ch>=65&&ch<=90)
     {
         countUpper=countUpper+1;
     }
     else if (ch>=48&&ch<=57)
     {
         countDigit=countDigit+1;
     }
     else
     {
          countSpecial=countSpecial+1;
     }
 }
        System.out.println(len);
        double d=((countLower*100.0)/len);
        System.out.println("The count and percentage of LowerCase letters is="+countLower +" "+d);
        System.out.println("The count and percentage of UpperCase letters is="+countUpper +" "+((countUpper*100.0)/len));
        System.out.println("The count and percentage of Digits letters is="+countDigit +" "+((countDigit*100)/len));
        System.out.println("The count and percentage of Special letters is="+countSpecial +" "+((countSpecial*100)/len));


    }
}