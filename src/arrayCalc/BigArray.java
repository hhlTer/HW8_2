package arrayCalc;

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

    private  static double result;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final int SIZE = 80_000_000;
        int[] a = bigArray(SIZE);

        ExecutorService service = Executors.newFixedThreadPool(2);
        Runnable runnable = () -> result = sinCos(a);
        Callable<Double> callable = () -> sinCos(a);

        Scanner scanner = new Scanner(System.in);
        System.out.print("N> ");
        int n = scanner.nextInt();

        Future<Double> future = service.submit(callable); //ExexuteServise submit
        service.shutdown();
//Pool
        long d = new Date().getTime();
        for (int i = 0; i < n; i++)
            System.out.println("From future " + future.get()); //future.get
        long sub = new Date().getTime() - d;
        System.out.println("Time \n" + sub);

//Thread
        d = new Date().getTime();
        for (int i = 0; i < n; i++){
            Thread thread = new Thread(runnable);
            thread.start();
            thread.join();
            System.out.println("Thread method: " + result);
        }
        sub = new Date().getTime() - d;
        System.out.println("Time: " + sub);
    }

    private static void poolCalc(Callable<Double> sum, ExecutorService service){
        long d = new Date().getTime();
        Future<Double> f = service.submit(sum);
        double result = 0;
        try {
            result = f.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Pool method:");
        System.out.println("Result: " + result);
        long sub = new Date().getTime() - d;
        System.out.println("Time: " + sub);
//        service.shutdown();
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
}
