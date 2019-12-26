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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Class represents exit screen
 */
public class AddExitScreen extends Main {
  /**
   * Exit screen
   * 
   * @param pane
   */
  public static void setUpExitScreen(BorderPane pane) {
    VBox vbox = new VBox();
    HBox hbox = new HBox();
    Text text = new Text("Quit");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    pane.setTop(text);
    Text exitMessage = new Text("Would you like to save questions?");
    exitMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    Button saveQuit = new Button("Save");
    Button noSaveQuit = new Button("Don't save");
    Button cancelQuit = new Button("Cancel");
    hbox.getChildren().addAll(saveQuit, noSaveQuit, cancelQuit);
    hbox.setAlignment(Pos.CENTER);
    hbox.setSpacing(10);
    vbox.getChildren().addAll(exitMessage, hbox);
    vbox.setSpacing(20);
    vbox.setAlignment(Pos.CENTER);

    // Detects if all changes have been saved.
    // This boolean variable is always changing properly according to user's action
    if (saved) {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Note");
      alert.setHeaderText("There is no change not been saved. Click on button below to close.");
      alert.showAndWait();
      stage.close();
    }

    // Call save action
    saveQuit.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        setupScreens("save");
        activate("save");
        needQuit = true;
      }
    });

    // Close directly
    noSaveQuit.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        stage.close();
      }
    });

    // Cancel exit action
    cancelQuit.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        main.setRoot(root);
      }
    });
    pane.setCenter(vbox);
    pane.setMargin(pane.getTop(), insets);
    pane.setMargin(pane.getCenter(), insets);
  }
}
