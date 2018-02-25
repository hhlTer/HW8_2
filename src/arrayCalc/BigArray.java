package arrayCalc;

import java.util.*;
import java.util.concurrent.*;

public class BigArray {


    private static int[] bigArray(int size){
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = i+1;
        }
        return a;
    }

    private static double result;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final int SIZE = 5_000_000;
        int[] mainArray = bigArray(SIZE); //fillArray

//        Callable<Double> callable = () -> sinCos(mainArray);//calc in callable
//Dialog
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter count of calculate > ");
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter count of thread > ");
        int t = scanner.nextInt();
        scanner.close();

        ExecutorService executor = Executors.newFixedThreadPool(t);
        CallableMethod callableMethod = new CallableMethod(mainArray);

//-----------------------------------------------------------------------------
        for (int i = 0; i < n; i++) {
            double result;
            //-------------Pool-------------------
            System.out.println("Caculate " + (i + 1) + ":");
            System.out.println("------------------------------------------------");
            long checkPoint = System.currentTimeMillis();
            Future<Double> future = executor.submit(callableMethod);
            System.out.print("Pool result: ");
            result = future.get();                                  //get
            checkPoint = System.currentTimeMillis() - checkPoint;
            System.out.println(result);
            System.out.println("Time: \n" + checkPoint);
            System.out.println("------------------------------------------------");

            //--------------Thread--------------------
            ThreadMethod threadMethod = new ThreadMethod(mainArray);

            checkPoint = System.currentTimeMillis();
            System.out.print("Threads result: ");
            result = threadMethod.result(t);
            checkPoint = System.currentTimeMillis() - checkPoint;

            System.out.println(result);
            System.out.println("Time: " + checkPoint);
            System.out.println("================================================\n");
        }
        executor.shutdown();
    }
}
