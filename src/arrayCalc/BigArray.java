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

        ExecutorService executor = Executors.newFixedThreadPool(t);

        CallableMethod callableMethod = new CallableMethod(a);

//-----------------------------------------------------------------------------
        for (int i = 0; i < n; i++) {
            System.out.println("Caculate " + (i + 1) + ":");
            System.out.println("------------------------------------------------");
            long checkPoint = System.currentTimeMillis();

            Future<Double> future = executor.submit(callableMethod);
            System.out.println("Pool result: " + future.get());
            checkPoint = System.currentTimeMillis() - checkPoint;
            System.out.println("Time \n" + checkPoint);
            System.out.println("------------------------------------------------");

            checkPoint = System.currentTimeMillis();
            double result = 0;
            ThreadMethod[] threadMethods = new ThreadMethod[t];
            for (int j = 0; j < t; j++) {
                int start = j * a.length / n;
                int fin = (j + 1) * a.length / n;
                int[] array = Arrays.copyOfRange(a, start, fin);
                threadMethods[j] = new ThreadMethod(array);
            }

            for (ThreadMethod th:
                 threadMethods) {
                th.join();//---------------------чому не чекає завершення тут?
                result += th.getResult();
            }

            System.out.println("Thread(s) result: " + result);
            checkPoint = System.currentTimeMillis() - checkPoint;
            System.out.println("Time \n" + checkPoint);
            System.out.println("================================================\n");
        }
        executor.shutdown();
    }
}
