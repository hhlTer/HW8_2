package calc;

import java.util.Scanner;
import java.util.concurrent.*;

public class Calc {
    private static double sum(double a, double b){
        return a + b;
    }
    private static double sub(double a, double b){
        return a - b;
    }
    private static double mlt(double a, double b){
        return a * b;
    }
    private static double dev(double a, double b){
        return a / b;
    }
    private static double prc(double a, double b){
        return b*100 / a;
    }
    private static boolean bol(double a, double b){
        return (a == b);
    }
    private static boolean big(double a, double b){
        return (a > b);
    }
    private static boolean sml(double a, double b){
        return (a < b);
    }

    private static double futureTask(double a, double b, char o) throws ExecutionException, InterruptedException{
        ExecutorService service = Executors.newFixedThreadPool(1);
        Callable<Double> calc;
            switch (o) {
                case '+':
                    calc = () -> sum(a, b);
                    break;
                case '-':
                    calc = () -> sub(a, b);
                    break;
                case '*':
                    calc = () -> mlt(a, b);
                    break;
                case '/':
                    calc = () -> dev(a, b);
                    break;
                case '%':
                    calc = () -> prc(a, b);
                    break;
                    default: calc = () -> null;
            }
        Future<Double> future = service.submit(calc);
        double result = future.get();
        service.shutdown();
        return result;
    }

    private static boolean futureTask(double a, double b, String operation) throws ExecutionException, InterruptedException{
        ExecutorService service = Executors.newFixedThreadPool(1);
        Callable<Boolean> calc;
        switch (operation) {
            case "==":
                calc = () -> bol(a, b);
                break;
            case ">":
                calc = () -> big(a, b);
                break;
            case "<":
                calc = () -> sml(a, b);
                break;
            default:
                calc = () -> null;
        }
        Future<Boolean> future = service.submit(calc);
        boolean result = future.get();
        service.shutdown();
        return result;
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter first digit: ");
                double a = scanner.nextDouble();

                System.out.print("Enter second digit: ");
                double b = scanner.nextDouble();

                System.out.print("Math operation: ");
                scanner.nextLine();
                String operation = scanner.nextLine();
                if (operation.equals("==") || operation.equals("<") || operation.equals(">"))
                    System.out.println(futureTask(a, b, operation));
                else System.out.println(futureTask(a, b, operation.charAt(0)));
            }catch (Exception e){
                System.out.println("Wrong data\nBye");
                break;
            }
        }
    }
    }

