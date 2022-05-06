package edu.northeast.imageConverter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;

public class CalcuController {

    @FXML
    public Label lblResult;
    @FXML
    public TextField txtRight;
    @FXML
    public TextField txtLeft;
    @FXML
    private Label welcomeText;

    int num1 = 0;
    int num2 = 0;

    @FXML
    public void onPlus(ActionEvent actionEvent) {
        processCalculation(edu.northeast.imageConverter.Operations.Plus, false);
    }

    @FXML
    public void onSubtract(ActionEvent actionEvent) {
        processCalculation(Operations.Subtract, false);
    }

    @FXML
    public void onMultiply(ActionEvent actionEvent) {
        processCalculation(Operations.Multiply, false);
    }

    @FXML
    public void onDevide(ActionEvent actionEvent) {
        processCalculation(Operations.Devide, true);
    }

    @FXML
    public void onClear(ActionEvent actionEvent) {
        lblResult.setStyle("-fx-background-color: #84D68B; -fx-text-fill: #EFFFF5;");
        lblResult.setText(StringUtils.EMPTY);
        txtLeft.setText(StringUtils.EMPTY);
        txtRight.setText(StringUtils.EMPTY);
    }

    private void processCalculation(Operations operation, boolean isDivide) {
        if (!validate(isDivide)) {
            return;
        }
        try {
            String oper = "";
            int result = 0;
            switch (operation) {
                case Plus:
                    oper = "+";
                    result = num1 + num2;
                    break;
                case Subtract:
                    oper = "-";
                    result = num1 - num2;
                    break;
                case Multiply:
                    oper = "*";
                    result = num1 * num2;
                    break;
                case Devide:
                    oper = "/";
                    result = num1 / num2;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + operation);
            }
            lblResult.setStyle("-fx-background-color: #84D68B; -fx-text-fill: #EFFFF5;");
            lblResult.setText(String.format("%d %s %d = %d", num1, oper, num2, result));
        } catch(Exception e) {
            showErrors(operation + " exception, please check.");
        }
    }


    private void showErrors(String msg) {
        lblResult.setStyle("-fx-text-fill: #FDF8F3; -fx-background-color: #D94823");
        lblResult.setText(msg);
    }

    private boolean validate(boolean isDivide) {
        String left = txtLeft.getText();
        String right = txtRight.getText();
        if (StringUtils.isBlank(left) || StringUtils.isBlank(right)) {
            showErrors("Input can NOT be NULL.");
            return false;
        }

        try {
            num1 = Integer.parseInt(left);
            num2 = Integer.parseInt(right);
        } catch(NumberFormatException ex) {
            showErrors("Incorrect Numbers input!");
            return false;
        }

        if (isDivide && num2 == 0) {
            showErrors("The devider can NOT be 0!");
            return false;
        }

        return true;
    }


}