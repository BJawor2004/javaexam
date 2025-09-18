package org.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.KeyCombination;
import javax.swing.*;
import java.io.*;

public class HelloApplication extends Application {
    private TextArea textArea;
    @Override
    public void start(Stage primaryStage) throws IOException {
        textArea = new TextArea();

        MenuBar menu = new MenuBar();
        Menu menuFile = new Menu("File");

        Menu menuFind = new Menu("Find");

        MenuItem newFile = new MenuItem("New");
        newFile.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));

        MenuItem openFile = new MenuItem("Open");
        openFile.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));

        MenuItem saveFile = new MenuItem("Save");
        saveFile.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));

        MenuItem exitApp = new MenuItem("Exit");
        exitApp.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));

        MenuItem findText = new MenuItem("Find...");
        findText.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));

        menuFile.getItems().addAll(newFile, openFile, saveFile, new SeparatorMenuItem(), exitApp);
        menuFind.getItems().add(findText);
        menu.getMenus().addAll(menuFile, menuFind);

        newFile.setOnAction(e -> textArea.clear());
        openFile.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pliki tekstowe", "*.txt"));
            File file = chooser.showOpenDialog(primaryStage);
            if(file != null)
            {
                try(BufferedReader reader = new BufferedReader(new FileReader(file)))
                {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null)
                    {
                        content.append(line).append("\n");
                    }
                    textArea.setText(content.toString());
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        saveFile.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pliki tekstowe", "*.txt"));
            File file = chooser.showSaveDialog(primaryStage);
            if(file != null)
            {
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
                {
                    writer.write(textArea.getText());
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        exitApp.setOnAction(e -> primaryStage.close());

        menuFind.getItems().add(findText);

        findText.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Find");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter text to find:");

            dialog.showAndWait().ifPresent(query -> {
                String content = textArea.getText();
                int index = content.indexOf(query);
                if (index >= 0) {
                    textArea.selectRange(index, index + query.length()); // zaznacza znaleziony tekst
                    textArea.requestFocus();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Not found");
                    alert.setHeaderText(null);
                    alert.setContentText("Text not found.");
                    alert.showAndWait();
                }
            });
        });

        BorderPane border = new BorderPane();
        border.setTop(menu);
        border.setCenter(textArea);

        Scene scene = new Scene(border, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
