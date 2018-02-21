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
        final int SIZE = 80_000_000;
        int[] a = bigArray(SIZE); //fillArray

        Callable<Double> callable = () -> sinCos(a);//calc in callable
//Dialog
        Scanner scanner = new Scanner(System.in);
        System.out.print("N> ");
        int n = scanner.nextInt();
//-----------------------------------------------------------------------------
        ExecutorService service = Executors.newFixedThreadPool(n); //create pool
        Future<Double> future = service.submit(callable); //submit in future
        service.shutdown();//and shutdown

//---------------------------Pool start-----------------------------------------
        long d = new Date().getTime();
        System.out.println("From pool: " + future.get());
        long sub = new Date().getTime() - d;
        System.out.println("Time \n" + sub);

//-------------------------Thread start-----------------------------------------
        d = new Date().getTime();
        for (int i = 0; i < n; i++) {
            int start = i * a.length/n;
            int fin = (i+1)*a.length/n;
            int[] array = Arrays.copyOfRange(a, start, fin);//separate array
            SumArrayThread arrayThread = new SumArrayThread(array);
            arrayThread.start();
            arrayThread.join();
            result += arrayThread.getSum();
        }
        sub = new Date().getTime() - d;
        System.out.println("\nThread result: " + result);
        System.out.println("Time: " + sub);
    }

    static double sinCos(int[] a){
        double sum = 0;
        for (int anA : a) sum += Math.sin(anA) + Math.cos(anA);
        return sum;
    }
}
