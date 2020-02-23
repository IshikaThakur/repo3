/*
WAP to produce NoClassDefFoundError and ClassNotFoundException exception.
*/



package java2.question3;

public class question3 {
    public static void main(String[] args) {
        try
        {
            Class.forName("java2.question3.Ishika");// this will give ClassNotFoundException because class Ishika is not
                                    // founded during compilation
        }
        catch(ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }
}
