package Java8;

public class Ques8_MutipleInheritance implements MultipleInherit1, MultipleInherit2 {
    @Override
    public void show() {
        MultipleInherit1.super.show();
        MultipleInherit2.super.show();
    }

    public static void main(String[] args) {
        Ques8_MutipleInheritance q8_mutipleInheritance = new Ques8_MutipleInheritance();
        q8_mutipleInheritance.show();
    }
}

interface MultipleInherit1 {
    default void show() {
        System.out.println("Function of First Interface");
    }

}

interface MultipleInherit2 {
    default void show() {
        System.out.println("Function of Second Interface");
    }
}