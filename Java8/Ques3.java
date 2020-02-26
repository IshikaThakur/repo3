package Java8;

@FunctionalInterface
interface GenericInterface {
    void manipulation(int a, int b);
}

class UseStatic {
     void sum(int a, int b) {
        System.out.println("sum is: " + (a + b));
    }

     void sub(int a, int b) {
        if (a > b)
            System.out.println("difference is: " + (a - b));
        else
            System.out.println("difference is: " + (b - a));
    }

   static  void mult(int a, int b) {
        System.out.println("product is: " + (b * a));
    }
}

public class Ques3 {
    public static void main(String[] args) {
        GenericInterface genericInterface = new UseStatic()::sum;
        GenericInterface genericInterface1 = new UseStatic()::sub;          //reference is created
        GenericInterface genericInterface2 =  UseStatic::mult;           //obj with class name is called
        genericInterface.manipulation(10, 20);
        genericInterface1.manipulation(10, 40);
        genericInterface2.manipulation(5, 4);
    }
}