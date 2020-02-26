package Java8;

interface Interface
{
    default void display()
    {
        System.out.println("default method ");
    }

    static void display1()
    {
        System.out.println("static method ");
    }
}
public class Ques6 implements Interface {
    public static void main(String[] args)
    {
        Ques6 obj = new Ques6();
        obj.display();
        Interface.display1();
    }
}
