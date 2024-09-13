package calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    OperationsController op = new OperationsController();

    @FXML
    private TextField display;
    @FXML
    private Label formula;

    private String currentOperation = "";
    private double firstOperand = 0;
    private boolean isNewOperation = false;
    private StringBuilder currentFormula = new StringBuilder();

    private void resetCalc() {
        display.clear();
        firstOperand = 0;
        formula.setText("");
        currentFormula.setLength(0);
        currentOperation = "";
        isNewOperation = false;
    }

    private String formatNumber(double n) {
        if (n % 1 == 0) {
            return String.format("%.0f", n);
        } else {
            return String.valueOf(n);
        }
    }

    @FXML
    private void handleButtonClick(javafx.event.ActionEvent event) {
        String buttonText = ((javafx.scene.control.Button) event.getSource()).getText();

        switch (buttonText) {
            case "C":
                resetCalc();
                break;
            case "=":
                if (!display.getText().isEmpty() && !currentOperation.isEmpty()) {
                    calculate();
                    currentOperation = "";
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "^":
                if (!display.getText().isEmpty()) {
                    if (!currentOperation.isEmpty() && !isNewOperation) {
                        // Если есть текущая операция, выполните вычисление перед новой операцией
                        calculate();
                    }
                    // Установите новую операцию
                    firstOperand = Double.parseDouble(display.getText());
                    currentOperation = buttonText;
                    isNewOperation = true;
                    currentFormula.append(" ").append(currentOperation).append(" ");
                    formula.setText(currentFormula.toString());
                }
                break;
            case "^2":
            case "sqrt":
            case "%":
                if (!display.getText().isEmpty()) {
                    firstOperand = Double.parseDouble(display.getText());
                    currentOperation = buttonText;
                    calculate(); // Немедленное выполнение для ^2, sqrt, %
                    currentOperation = ""; // Очистите операцию, так как она завершена
                }
                break;
            default:
                if (isNewOperation) {
                    display.clear();
                    isNewOperation = false;
                }
                display.appendText(buttonText);
                currentFormula.append(buttonText);
                formula.setText(currentFormula.toString());
                break;
        }
    }

    private void calculate() {
        if (!display.getText().isEmpty() && !currentOperation.isEmpty()) {
            double secondOperand = Double.parseDouble(display.getText());
            double res = 0;

            switch (currentOperation) {
                case "+":
                    res = op.plus(firstOperand, secondOperand);
                    break;
                case "-":
                    res = op.minus(firstOperand, secondOperand);
                    break;
                case "*":
                    res = op.multiply(firstOperand, secondOperand);
                    break;
                case "/":
                    if (secondOperand != 0) {
                        res = op.div(firstOperand, secondOperand);
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
                case "^":
                    res = op.pow(firstOperand, secondOperand);
                    break;
                case "^2":
                    res = op.powOnTwo(firstOperand);
                    break;
                case "sqrt":
                    res = op.sqrt(firstOperand);
                    break;
                case "%":
                    res = op.percent(firstOperand);
                    break;
            }

            // Установите результат в дисплей и обновите формулу
            display.setText(formatNumber(res));
            currentFormula.append(" = ").append(formatNumber(res));
            formula.setText(currentFormula.toString());
            currentFormula.setLength(0);
            currentFormula.append(formatNumber(res));
            isNewOperation = true;
        }
    }
}