/**
 * Filename: UserInterface.java Project: Quiz Generator 
 * Authors: Aaron Zhang, Aurora Shen, Tyler Gu, Yixing Tu 
 * Group: A-Team 68
 * 
 * UserInterface class is GUI class for this project.
 * 
 */

package application;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Class represents question loading screen
 */
public class AddLoadScreen extends Main {
  /**
   * This screen is in charge of reading file before loading questions
   * 
   * @param pane is the pane we are setting up
   */
  public static void setUpBeforeLoadingScreen(BorderPane pane) {
    BorderPane currScreen = pane;
    Text text = new Text("Load question");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    currScreen.setTop(text); // Set top title of this screen

    HBox hbox = new HBox();
    TextField fileName = new TextField(); // Textfiled used to read file name from user
    fileName.setPromptText("e.g. questions_001.json");
    hbox.getChildren().addAll(new Text("Question file: "), fileName);
    hbox.setAlignment(Pos.CENTER);
    currScreen.setCenter(hbox);

    HBox buttons = new HBox();

    // Button used to load questions from file
    Button loadButton = new Button("Load");
    loadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        String inputFileName = fileName.getText();
        try {
          if (!filesOpened.contains(inputFileName)) { // Don't read files have been read in
            // Add questions to question bank in generator
            quizGenerator.addQuestionFromFile("questionFile/"+inputFileName);
            filesOpened.add(inputFileName);
            main.setRoot(root);
            saved = false; // Successfully read means there is unsaved changes
          } else { // Warn user that the file entered has been read in already
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("The file has been read");
            alert.setContentText("Please enter a different file");
            alert.showAndWait();
            fileName.clear();
          }
        } catch (IOException | ParseException e) { // Handle exception
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Alert");
          alert.setHeaderText("Cannot open or read file");
          alert.setContentText("Please check the file name you entered");
          alert.showAndWait();
          fileName.clear();
        }
      }
    });

    // Cancel reading file and go back to root
    Button cancelButton = new Button("Cancel");
    cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        main.setRoot(root);
      }
    });


    buttons.getChildren().addAll(loadButton, cancelButton);
    buttons.setAlignment(Pos.CENTER_RIGHT);
    buttons.setSpacing(10);
    currScreen.setBottom(buttons);
    currScreen.setAlignment(buttons, Pos.CENTER_RIGHT);
    pane.setMargin(pane.getTop(), insets);
    pane.setMargin(pane.getCenter(), insets);
    pane.setMargin(pane.getBottom(), insets);
  }
}
