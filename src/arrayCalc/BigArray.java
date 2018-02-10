package arrayCalc;

import java.util.Date;
import java.util.concurrent.*;

public class BigArray {


    private static int[] bigArray(int size){
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = i+1;
        }
        return a;
    }

    private  static double result;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final int SIZE = 80_000_000;
        int[] a = bigArray(SIZE);
        ExecutorService service = Executors.newFixedThreadPool(2);

        long d = new Date().getTime();

        Callable<Double> sum = () -> {
            double sum1 = 0;
            for (int i = 0; i < SIZE; i++) {
                double sin = Math.sin((double) a[i]);
                double cos = Math.cos((double) a[i]);
                sum1 +=sin + cos;
            }
            return sum1;
        };

        Future<Double> f = service.submit(sum);
        System.out.println("Pool method:");
        System.out.println("Result: " + f.get());
        long sub = new Date().getTime() - d;
        System.out.println("Time: " + sub);
        service.shutdown();

        d = new Date().getTime();
        Runnable runnable = () -> result = sinCos(a);
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Thread method:");
        thread.join();
        System.out.println("Result: " + result);
        sub = new Date().getTime() - d;
        System.out.println("Time: " + sub);
    }

    private static double sinCos(int[] a){
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            double sin = Math.sin((double) a[i]);
            double cos = Math.cos((double) a[i]);
            sum += sin + cos;
        }
        return sum;
    }

//    private static int sinCos(int[] a, int start, int finish){
//        double sum = 0;
//        for (int i = start; i < finish; i++) {
//            double sin = Math.sin((double) a[i]);
//            double cos = Math.sin((double) a[i]);
//            sum += sin + cos;
//        }
//        return (int) sum;
//    }
}
