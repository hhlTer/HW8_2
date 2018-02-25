package arrayCalc;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class ThreadMethod {
    ThreadMethod(int[] mainArray){
        this.array = mainArray;
    }
    private int array[];

    double result(int threads) throws ExecutionException, InterruptedException {
        double result = 0;

        final int ArrayAize = array.length;
        final int arrayength = array.length / threads;
        FutureTask<Double>[] futureTasks = new FutureTask[threads];

        for (int i = 0; i < threads; i++) {
            final int start = i * arrayength;
            final int finsh = (i + 1) * arrayength;
            int[] halfArray = Arrays.copyOfRange(array, start, finsh);

            CallableMethod callableMethod = new CallableMethod(halfArray);
            futureTasks[i] = new FutureTask<>(callableMethod);
            Thread thread = new Thread(futureTasks[i]);
            thread.start();
        }
        for (int i = 0; i < threads; i++) {
            result += futureTasks[i].get();
        }
        return result;
    }
}