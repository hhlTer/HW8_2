package arrayCalc;

public class SumArrayThread extends Thread{
    private int[] array;
    private double sum = 0;
    SumArrayThread (int[] array){
        this.array = array;
    }
@Override
    public void run(){
        sum = BigArray.sinCos(array);
    }

    double getSum() {
        return sum;
    }
}
