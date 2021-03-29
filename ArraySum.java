// 
// Sequential sum program.
//
// Programação Concorrente (CC3037), DCC/FCUP
// Eduardo R. B. Marques
//
import java.util.Random;

public class ArraySum {
  
  public static void main(String[] args) {
    int size = args.length >= 1 ? 
      Integer.parseInt(args[0]) : 10000;
    int numberOfThreads = args.length >= 2 ?
      Integer.parseInt(args[1]) : 2;

    int[] array = buildArray(size);

    long t = System.currentTimeMillis();
    int sum = sumArray(numberOfThreads, array);
    t = System.currentTimeMillis() - t;

    System.out.printf("Result=%d in %d ms%n", sum, t);
  }

  static int[] buildArray(int size) {
    int[] array = new int[size];
    Random r = new Random(50);
    for (int i = 0; i < size; i++) {
      array[i] = -100 + r.nextInt(201); // -100 to 100
    }
    return array;
  }

  static int sumArray(int numberOfThreads, int[] array) {
    // Sequential version (numberOfThreads ignored)
    int sum = 0;
    for (int i = 0; i < array.length; i++) {
      sum += array[i];
    }
    return sum;
  }
}