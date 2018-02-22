package arrayCalc;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
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
        final int SIZE = 8_000_000;
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

//-----------------------------------------------------------------------------
        for (int i = 0; i < n; i++) {
            System.out.println("Caculate " + (i+1) + ":");
            System.out.println("------------------------------------------------");
            long checkPoint = new Date().getTime();
            System.out.println("Pool result: " + calcInPool(a, t));
            checkPoint = new Date().getTime() - checkPoint;
            System.out.println("Time \n" + checkPoint);
            System.out.println("------------------------------------------------");
            checkPoint = new Date().getTime();
            System.out.println("Thread(s) result: " + calcInTreads(a, t));
            checkPoint = new Date().getTime() - checkPoint;
            System.out.println("Time \n" + checkPoint);
            System.out.println("================================================\n");
        }
        service.shutdown();
    }

    private static double sinCos(int[] a){
        double sum = 0;
        for (int anA : a) sum += Math.sin(anA) + Math.cos(anA);
        return sum;
    }
    private static ExecutorService service;

    private static double calcInPool(int[] a, int t) throws ExecutionException, InterruptedException {
        if (service == null) service = Executors.newFixedThreadPool(t);
        Future<Double> future = service.submit(callable(a));
        return future.get();
    }
    private static double calcInTreads(int[] a, int n) throws ExecutionException, InterruptedException {
        double result = 0;
        for (int i = 0; i < n; i++) {
            int start = i * a.length / n;
            int fin = (i + 1) * a.length / n;
            int[] array = Arrays.copyOfRange(a, start, fin);//separate array
            FutureTask<Double> futureTask = new FutureTask<>(callable(array));
            new Thread(futureTask).start();
            result += futureTask.get();
        }
        return result;
    }

    private static Callable<Double> callable(int[] array){
        Callable<Double> callable = () -> sinCos(array);
        return callable;
    }
}
