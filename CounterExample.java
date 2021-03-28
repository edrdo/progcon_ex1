// 
// Counter example.
//
// Programação Concorrente (CC3037), DCC/FCUP
// Eduardo R. B. Marques
//
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterExample {
  public static void main(String[] args) throws InterruptedException {
    int n = args.length >= 1 ? Integer.parseInt(args[0]) : 2;
    int k = args.length >= 2 ? Integer.parseInt(args[1]) : 100;
    test(new BuggyCounter(), n, k);
    test(new CorrectCounter(), n, k);
    test(new CorrectCounter2(), n, k);
    test(new CorrectCounter3(), n, k);
    test(new CorrectCounter4(), n, k);
    test(new CorrectCounter5(), n, k);
  }
  static void test(Counter c, int n, int k) throws InterruptedException {
    System.out.println("=> Testing " + c.getClass().getName());
    Thread[] t = new Thread[n];
    for (int i = 0; i < n; i++) {
      t[i] = new Thread(() -> {
        for (int j = 0; j < k; j++) c.increment();
      });
      t[i].start();
    }
    for (int i = 0 ; i < n; i++) {
      t[i].join();
    }
    System.out.printf("expected counter value: %d%n", n*k);
    System.out.printf("actual counter value  : %d%n", c.getValue());
  }
}

interface Counter {
  void increment();
  int getValue();
}

class BuggyCounter implements Counter{
  private int value = 0;
  public void increment() { value ++; }
  public int getValue()   { return value; }
}

class CorrectCounter implements Counter {
  private int value = 0;
  public synchronized void increment() { value ++; }
  public synchronized int getValue()   { return value; } 
}
class CorrectCounter2 implements Counter {
  private int value = 0;
  private final ReentrantLock rl = new ReentrantLock();

  public void increment() { 
    rl.lock();
    try { 
      value ++; 
    }
    finally { 
      rl.unlock(); 
    }
  }
  public int getValue()   { 
    rl.lock();
    try {
      return value; 
    } 
    finally {
      rl.unlock();
    }
  } 
}

class CorrectCounter3 implements Counter {
  private int value = 0;
  private final ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();

  public void increment() { 
    rrwl.writeLock().lock();
    try { 
      value ++; 
    }
    finally { 
      rrwl.writeLock().unlock();
    }
  }
  public int getValue()   { 
    rrwl.readLock().lock();
    try {
      return value; 
    } 
    finally {
      rrwl.readLock().unlock();
    }
  } 
}

class CorrectCounter4 implements Counter {
   private final AtomicInteger value = new AtomicInteger(0);
   public void increment() {
     int v;
     do {
      v = value.get();
     } while(! value.compareAndSet(v, v+1));
   }
   public int getValue() {
     return value.get();
   }
}

class CorrectCounter5 implements Counter {
   private final AtomicInteger value = new AtomicInteger(0);
   public void increment() {
     value.incrementAndGet();
   }
   public int getValue() {
     return value.get();
   }
}
