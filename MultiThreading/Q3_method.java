//  Using Synchronized Method
package Multithreading;
class Counter
{
     int count;
    public synchronized void increment()
    {
        count=count+1;
    }
}
public class SynchronizedConcept  {
    public static void main(String[] args) throws Exception{
        {
            Counter c=new Counter();
            Thread t1=new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<100;i++)
                    {
                        c.increment();
                }}
            });
            Thread t2=new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<100;i++)
                    {
                        c.increment();
                    }}
            });
            t1.start();
            t2.start();
           t1.join();
           t2.join();

            System.out.println("Count" +" "+c.count);
        }
    }
}
