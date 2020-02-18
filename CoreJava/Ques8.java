import java.util.*;
public class Ques8 {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        Scanner in=new Scanner(System.in);
        System.out.println("Enter a string: ");
        sb.append(in.nextLine());
            System.out.println("string: " + sb);
            System.out.println("reverse: " + sb.reverse());
            System.out.println(sb.delete(4,9));
        }
    }

