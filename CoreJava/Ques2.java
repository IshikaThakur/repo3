import java.util.Arrays;
import java.util.Scanner;

public class Ques2
{
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String str;
        int i,count=0,j;
        System.out.println("Enter the string");
         str=sc.nextLine();
        str=str.toLowerCase();
        String words[]=str.split(" ");
        for(i=0;i<words.length;i++)
        {
            count=1;
            for(j=i+1;j<words.length;j++)
            {
                if(words[i].equals(words[j]))
                {
                    count++;
                    words[j]="0";
                }
            }
            if(count>1&& words[i]!="0")
                System.out.println(words[i]);
        }



    }

}
