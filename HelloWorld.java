// 
// Hello world of threads! 
//
// Programação Concorrente (CC3037), DCC/FCUP
// Eduardo R. B. Marques
//
import java.util.Scanner;
public class HelloWorld {
  public static void main(String[] args) throws InterruptedException {
    hello1();
    hello2();
    hello3();
    hello4();
  }

  // Example creation 1 (extension of class java.lang.Thread)
  static class HelloThread extends Thread {
    @Override
    public void run() {
      System.out.println("Hello from spawned thread");
    }
  }
  static void hello1() throws InterruptedException {
    System.out.println("=> Hello 1");
    HelloThread t = new HelloThread();
    t.start();
    System.out.println("Hello from main thread");
    t.join();
  }
  // Example creation 2 (use of Runnable instance)
  static class HelloRunnable implements Runnable {
    @Override
    public void run() {
      System.out.println("Hello from spawned thread");
    }
  }
  static void hello2() throws InterruptedException {
    System.out.println("=> Hello 2");
    Thread t = new Thread(new HelloRunnable()); 
    t.start();
    System.out.println("Hello from main thread");
    t.join();
  }
  // Example 3 (use of a Runnable instance defined by a lambda expression)
  static void hello3() throws InterruptedException {
    System.out.println("=> Hello 3");
    Thread t = new Thread(() -> {
      System.out.println("Hello from spawned thread");
    });
    
    t.start();
    System.out.println("Hello from main thread");
    t.join();
  }
  // Example 4 (more than one thread spawned) 
  static void hello4() throws InterruptedException {
    System.out.println("=> Hello 4");
    Scanner in = new Scanner(System.in);
    System.out.print("How many threads? ");
    Thread[] t = new Thread[in.nextInt()];
    for (int i = 0; i < t.length; i++) {
      int id = i + 1;
      t[i] = new Thread(() -> { 
        System.out.println("Hello from spawned thread " + id);
      });
      // t[i].setDaemon(true);
      t[i].start();
    }
    System.out.println("Hello from main thread");
    for (int i = 0; i < t.length; i++) {
      t[i].join();
    }
  }
}
