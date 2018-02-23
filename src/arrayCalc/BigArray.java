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
        final int SIZE = 2_000_000;
        int[] a = bigArray(SIZE); //fillArray

//        Callable<Double> callable = () -> sinCos(a);//calc in callable
//Dialog
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter count of calculate > ");
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter count of thread > ");
        int t = scanner.nextInt();
        scanner.close();

        ExecutorService executor = Executors.newFixedThreadPool(n);
        List<Future<Double>> list = new ArrayList<>();

        CallableMethod callableMethod = new CallableMethod(a);

//-----------------------------------------------------------------------------
        for (int i = 0; i < n; i++) {
            System.out.println("Caculate " + (i+1) + ":");
            System.out.println("------------------------------------------------");
            long checkPoint = System.currentTimeMillis();

            Future<Double> future = executor.submit(callableMethod);
            System.out.println("Pool result: " + future.get());
            checkPoint = System.currentTimeMillis() - checkPoint;
            System.out.println("Time \n" + checkPoint);
            System.out.println("------------------------------------------------");

            checkPoint = System.currentTimeMillis();
            RunnableMethod runnableMethod = new RunnableMethod(a);
            Thread thread = new Thread(runnableMethod);
            thread.start();
            thread.join();
            System.out.println("Thread(s) result: " + runnableMethod.getResult());
            checkPoint = System.currentTimeMillis() - checkPoint;
            System.out.println("Time \n" + checkPoint);
            System.out.println("================================================\n");
        }
        executor.shutdown();
    }
}
