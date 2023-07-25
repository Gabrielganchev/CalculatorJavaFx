import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class JavaFxCalculator extends Application {
    private Label displayLabel;

    private String currentOperator = "";
    private double firstOperand = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Calculator");

        GridPane gridPane = createGridPane();
        createDisplay(gridPane);
        createButtons(gridPane);

        Scene scene = new Scene(gridPane, 250, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        return gridPane;
    }

    private void createDisplay(GridPane gridPane) {
        displayLabel = new Label("0");
        displayLabel.setStyle("-fx-font-size: 20;");
        gridPane.add(displayLabel, 0, 0, 4, 1);
    }

    private void createButtons(GridPane gridPane) {
        String[][] buttonLabels = {
                { "7", "8", "9", "/" },
                { "4", "5", "6", "*" },
                { "1", "2", "3", "-" },
                { "0", ".", "=", "+" }
        };

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Button button = new Button(buttonLabels[row][col]);
                button.setMinSize(50, 50);
                button.setStyle("-fx-font-size: 16;");

                if (Character.isDigit(buttonLabels[row][col].charAt(0)) || buttonLabels[row][col].equals(".")) {
                    button.setOnAction(e -> handleNumericButton(buttonLabels[row][col]));
                } else if (buttonLabels[row][col].equals("=")) {
                    button.setOnAction(e -> handleEqualsButton());
                } else {
                    button.setOnAction(e -> handleOperatorButton(buttonLabels[row][col]));
                }

                gridPane.add(button, col, row + 1);
            }
        }
    }

    private void handleNumericButton(String value) {
        if (displayLabel.getText().equals("0")) {
            displayLabel.setText(value);
        } else {
            displayLabel.setText(displayLabel.getText() + value);
        }
    }

    private void handleOperatorButton(String operator) {
        firstOperand = Double.parseDouble(displayLabel.getText());
        currentOperator = operator;
        displayLabel.setText("0");
    }

    private void handleEqualsButton() {
        double secondOperand = Double.parseDouble(displayLabel.getText());
        double result = 0;

        switch (currentOperator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                result = firstOperand / secondOperand;
                break;
        }

        displayLabel.setText(String.valueOf(result));
        firstOperand = result;
        currentOperator = "";
    }
}

