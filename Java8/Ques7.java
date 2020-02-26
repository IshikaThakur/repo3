package Java8;

public class Ques7 implements OverridenInterface {
    @Override
    public void ToBeOverridden() {
        System.out.println("Function Overrided");
    }

    public static void main(String[] args) {
        Ques7 q7_override = new Ques7();
        q7_override.ToBeOverridden();

    }
}

interface OverridenInterface {
    default void ToBeOverridden() {
        System.out.println("Function to be Overridden");
    }
}