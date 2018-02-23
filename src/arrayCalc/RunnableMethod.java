package arrayCalc;

public class RunnableMethod implements Runnable {
    private int[] array;
    private double result;

    RunnableMethod(int[] array){
        this.array = array;
    }

    @Override
    public void run() {
        result = 0;
        for (int anA : array) result += Math.sin(anA) + Math.cos(anA);
    }

    public double getResult() {
        return result;
    }
}
