package other;

public class synchronizeTest implements Runnable {  
    private int c = 0;


    public void test() {
        // Create the object with the run() method
        Runnable runnable = new synchronizeTest();
        Runnable runnable2 = new synchronizeTest();
        // Create the thread supplying it with the runnable object
        Thread thread = new Thread(runnable,"thread-1");
        Thread thread2 = new Thread(runnable,"thread-2");
//      Here the key point is passing same object, if you pass runnable2 for thread2,
//      then its not applicable for synchronization test and that wont give expected
//      output Synchronization method means "it is not possible for two invocations
//      of synchronized methods on the same object to interleave"

        // Start the thread
        thread.start();
        thread2.start();
    }

    public synchronized  void increment() {
        System.out.println("Begin thread " + Thread.currentThread().getName());
        // -2, 2024260031
        //
        System.out.println(this.hashCode() + " | Value of C = " + c);
//      If we uncomment this for synchronized block, then the result would be different
        
        // if this is blocked, thread-1 and thread-2 both have a chance to be first; 
        // if not blocked these code, 
      synchronized(this) {
            for (int i = 0; i < 9999999; i++) {
                c += i;
                if(c%100000==0){
                	System.out.println("=="+Thread.currentThread().getName());
                }
            }
      }
        System.out.println("End thread " + Thread.currentThread().getName());
    }

//    public synchronized void decrement() {
//        System.out.println("Decrement " + Thread.currentThread().getName());
//    }

    public int value() {
        return c;
    }

    @Override
    public void run() {
        this.increment();
    }
}