import java.util.Scanner;

public class Ques5 {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int n,i,j;
        System.out.println("enter the size of array");
        n=sc.nextInt();
        int arr1[]=new int[n];
        int arr2[]=new int[n];
        //int arr3[]=new int[n];
        System.out.println("enter the elements in array1");
        for(i=0;i<arr1.length;i++)
        {
            arr1[i]=sc.nextInt();
        }
        System.out.println("enter the elements in array2");
        for(j=0;j<arr1.length;j++)
        {
            arr2[j]=sc.nextInt();
        }
        for (i=0;i<arr1.length;i++)
        {
            for(j=0;j<arr2.length;j++)
            {
                if(arr1[i]==arr2[j])
                {
                    System.out.println(arr1[i]);
                }
            }
        }
    }
}
