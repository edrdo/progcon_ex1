// 
// Threads: sleeping and use of interrupts.
//
// Programação Concorrente (CC3037), DCC/FCUP
// Eduardo R. B. Marques
//
public class SleepAndInterrupt {
  public static void main(String[] args) throws InterruptedException {
    Thread t = new Thread( () -> {
       try {
         System.out.println("going to sleep ...");
         Thread.sleep(1000);
         System.out.println("awake ...");
       }
       catch (InterruptedException e) {
         System.out.println("got interrupted!");
       }
    });
    t.start();
    Thread.sleep(999);
    t.interrupt();
    t.join();
  }
}
