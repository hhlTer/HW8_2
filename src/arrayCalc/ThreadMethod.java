package arrayCalc;

public class ThreadMethod extends Thread{
    private int[] array;
    private double result;

    ThreadMethod(int[] array){
        this.array = array;
    }
    @Override
    public void run() {
        result = 0;
        for (int anA : array) result += Math.sin(anA) + Math.cos(anA);
    }

    double getResult() {
        return result;
    }
}