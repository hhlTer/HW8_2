package task2;

import java.util.concurrent.Callable;

public class CallableMethod implements Callable<Double> {
    private int[] array;
    CallableMethod(int[] array){
        this.array = array;
    }

    @Override
    public Double call() throws Exception {
        double sum = 0;
        for (int anA : array) sum += Math.sin(anA) + Math.cos(anA);
        return sum;
    }
}
