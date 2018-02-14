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
        int[] a = bigArray(SIZE);

        Callable<Double> callable = () -> sinCos(a);

        Scanner scanner = new Scanner(System.in);
        System.out.print("N> ");
        int n = scanner.nextInt();

        ExecutorService service = Executors.newFixedThreadPool(n);
        Future<Double> future = service.submit(callable); //ExexuteServise submit
        service.shutdown();
//Pool
        long d = new Date().getTime();
        System.out.println("From pool: " + future.get()); //future.get
        long sub = new Date().getTime() - d;
        System.out.println("Time \n" + sub);

//Thread
        d = new Date().getTime();
        for (int i = 0; i < n; i++) {
            int start = i * a.length/n;
            int fin = (i+1)*a.length/n;
            int[] array = Arrays.copyOfRange(a, start, fin);
            SumArrayThread arrayThread = new SumArrayThread(array);
            arrayThread.start();
            arrayThread.join();
            result += arrayThread.getSum();
        }
        sub = new Date().getTime() - d;
        System.out.println("\nThread result: " + result);
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
    }

    static double sinCos(int[] a){
        double sum = 0;
        for (int anA : a) sum += Math.sin(anA) + Math.cos(anA);
        return sum;
    }
}
