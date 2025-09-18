package org.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        TextField num1 = new TextField();
        num1.setPromptText("1");

        TextField num2 = new TextField();
        num2.setPromptText("2");

        Label result = new Label("Wynik: ");

        Button addbutton = new Button("+");
        Button subbutton = new Button("-");
        Button mulbutton = new Button("*");
        Button divbutton = new Button("/");
        Button powbutton = new Button("^");

        addbutton.setOnAction(e->calculate(num1, num2, result, "+"));
        subbutton.setOnAction(e->calculate(num1, num2, result, "-"));
        mulbutton.setOnAction(e->calculate(num1, num2, result, "*"));
        divbutton.setOnAction(e->calculate(num1, num2, result, "/"));
        powbutton.setOnAction(e->calculate(num1, num2, result, "^"));

        HBox inputbox = new HBox(10, num1, num2);
        HBox buttonbox = new HBox(10, addbutton, subbutton, mulbutton, divbutton, powbutton);
        VBox layout = new VBox(15, inputbox, buttonbox, result);

        Scene scene = new Scene(layout);
        stage.setTitle("Kalkulator");
        stage.setScene(scene);
        stage.show();
    }

    public void calculate(TextField n1, TextField n2, Label result, String operator)
    {
        try
        {
            double a = Double.parseDouble(n1.getText());
            double b = Double.parseDouble(n2.getText());
            double output = switch(operator)
            {
                case "+"->a+b;
                case "-"-> a-b;
                case "*"-> a*b;
                case "/"-> b!=0 ? a/b : Double.NaN;
                case "^"-> Math.pow(a,b);
                default -> 0;
            };
            result.setText("Wynik: " + output);
        }
        catch (NumberFormatException ex)
        {
            result.setText("Błąd, wpisz poprawne liczby");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
