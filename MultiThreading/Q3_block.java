//Synchronization Block
package Multithreading;

class Counter1 {

    int count;
    public void increment()
    {
        String tName=Thread.currentThread().getName();
        System.out.println(tName);
        synchronized(this){                                                    //synchronized block
        count=count+1;
    }}
}
public class SynchronizedBlock  {
    public static void main(String[] args) throws Exception{
        {
            Counter1 c=new Counter1();
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


