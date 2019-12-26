/**
 * Filename: UserInterface.java Project: Quiz Generator 
 * Authors: Aaron Zhang, Aurora Shen, Tyler Gu, Yixing Tu 
 * Group: A-Team 68
 * 
 * UserInterface class is GUI class for this project.
 * 
 */

package application;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Class represents saving screen
 */
public class AddSaveScreen extends Main {
  /**
   * The screen where user saves question bank
   * 
   * @param pane is the pane we are working on
   */
  public static void setUpSaveScreen(BorderPane pane) {
    HBox hbox = new HBox();
    Button save = new Button("Save");
    Button cancel = new Button("Cancel");
    hbox.getChildren().addAll(save, cancel);
    hbox.setAlignment(Pos.CENTER_RIGHT);
    hbox.setSpacing(10);

    pane.setBottom(hbox);

    VBox vbox = new VBox();
    TextField fileName = new TextField();
    // Prompt for a file name
    fileName.setPromptText("Enter a valid file name");
    vbox.getChildren().addAll(new Text("Filename:"), fileName);
    Text text = new Text("Save");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    pane.setTop(text);
    pane.setCenter(vbox);
    pane.setMargin(pane.getTop(), insets);
    pane.setMargin(pane.getCenter(), insets);
    pane.setMargin(pane.getBottom(), insets);

    // Saving action
    save.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        if (fileName.getText().isEmpty()) {
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Alert");
          alert.setHeaderText("You must enter a file name");
          alert.showAndWait();
        } else {
          try {
            quizGenerator.save(fileName.getText());
            saved = true;
          } catch (Exception e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Please enter a valid JSON file name.");
            alert.showAndWait();
            fileName.clear();
          }
          main.setRoot(root);
          if (needQuit) { // If user enters saving page after clicking on exit, quit.
            stage.close();
          }
        }
      }
    });

    cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        main.setRoot(root); // Go back to root without any action
      }
    });
  }
}
