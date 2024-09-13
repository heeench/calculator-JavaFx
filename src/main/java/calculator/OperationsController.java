package calculator;


public class OperationsController {

    public OperationsController() { }

    public double plus(double a, double b) {
        return a + b;
    }

    public double minus(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double div(double a, double b) { return a / b; }

    public double pow(double a, double b) { return Math.pow(a, b); }

    public double sqrt(double a) { return Math.sqrt(a); }

    public double powOnTwo(double a) { return Math.pow(a, 2); }

    public double percent(double a) { return a / 100; }
}
