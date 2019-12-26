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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Class represents adding question screen
 */
public class AddQuestionScreen extends Main {

  public AddQuestionScreen() {
    // Do nothing
  }
  
  /**
   * This method sets up the add screen when Add button is clicked
   * 
   * @param pane is the pane we are setting up
   */
  public static void setUpAddScreen(BorderPane pane) {
    saved = false; // update new change so that user will be asked to save
    VBox vbox = new VBox();
    TextField textField; // common textField reference to use in this method

    // Set the text at the top
    Text text = new Text("Add new question");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    pane.setTop(text);

    // initialize a HBox for text of the question
    // and add to the vbox
    HBox hbox = new HBox();
    TextField question = new TextField(); // text field for question
    question.setPromptText("Type in question");
    hbox.getChildren().addAll(new Text("Text: "), question);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER); // align to the center
    // initialize a new HBox for topic of the question
    hbox = new HBox();
    TextField topic = new TextField(); // text field for topic
    topic.setPromptText("Type in topic");
    hbox.getChildren().addAll(new Text("Topic: "), topic);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    // initialize a new HBox for Image file name of the question
    hbox = new HBox();
    TextField image = new TextField();
    image.setPromptText("Type in image name"); // text field for image path
    hbox.getChildren().addAll(new Text("Image: "), image);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    // initialize a new HBox for texts
    hbox = new HBox();
    hbox.getChildren().add(new Text("Choices: "));
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);

    // toggle group of radio buttons so that only one selection can be chosen
    ToggleGroup group = new ToggleGroup();
    // five radio buttons corresponding to five text fields
    RadioButton button1 = new RadioButton();
    RadioButton button2 = new RadioButton();
    RadioButton button3 = new RadioButton();
    RadioButton button4 = new RadioButton();
    RadioButton button5 = new RadioButton();
    // add buttons into togglegroup
    button1.setToggleGroup(group);
    button2.setToggleGroup(group);
    button3.setToggleGroup(group);
    button4.setToggleGroup(group);
    button5.setToggleGroup(group);
    button1.setSelected(true);

    // set up the text fields for user input
    TextField choice1 = new TextField();
    TextField choice2 = new TextField();
    TextField choice3 = new TextField();
    TextField choice4 = new TextField();
    TextField choice5 = new TextField();
    choice1.setPromptText("Choice 1");
    hbox = new HBox();
    hbox.getChildren().setAll(button1, choice1);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    choice2.setPromptText("Choice 2");
    hbox = new HBox();
    hbox.getChildren().setAll(button2, choice2);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    choice3.setPromptText("Choice 3");
    hbox = new HBox();
    hbox.getChildren().setAll(button3, choice3);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    choice4.setPromptText("Choice 4");
    hbox = new HBox();
    hbox.getChildren().setAll(button4, choice4);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    choice5.setPromptText("Choice 5");
    hbox = new HBox();
    hbox.getChildren().setAll(button5, choice5);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(10);

    // set up the buttons and their functionality
    Button saveButton = new Button("Add");
    Button cancelButton = new Button("Cancel");
    hbox = new HBox();
    hbox.getChildren().addAll(saveButton, cancelButton);
    hbox.setSpacing(10);
    hbox.setAlignment(Pos.CENTER_RIGHT);

    // when save button is clicked, check if any required field is empty
    // create a new question and save to question bank
    saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        main.setRoot(root);
        String[] choiceArray = new String[5];
        String answer;
        choiceArray[0] = choice1.getText();
        choiceArray[1] = choice2.getText();
        choiceArray[2] = choice3.getText();
        choiceArray[3] = choice4.getText();
        choiceArray[4] = choice5.getText();

        // if question text field or topic field is empty
        // show alert
        if (question.getText().isEmpty() || topic.getText().isEmpty()) {
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Alert");
          alert.setHeaderText("Empty Text Field!");
          alert.setContentText("Question Text can not be empty");
          activate("add");
          setupScreens("add");
          alert.showAndWait();
          return;
        }
        // image field is not required, if empty, set to "none"
        if (image.getText().isEmpty()) {
          image.setText("none");
        }

        // find the correct choice
        TextField rightTF;
        if (group.getSelectedToggle().equals(button1)) {
          rightTF = choice1;
        } else if (group.getSelectedToggle().equals(button2)) {
          rightTF = choice2;
        } else if (group.getSelectedToggle().equals(button3)) {
          rightTF = choice3;
        } else if (group.getSelectedToggle().equals(button4)) {
          rightTF = choice4;
        } else {
          rightTF = choice5;
        }

        // if correct choice is empty, display alert
        // else, set the correct answer
        if (rightTF.getText().isEmpty()) {
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Alert");
          alert.setHeaderText("Empty Right Choice Selected!");
          alert.setContentText("Empty choice cannot be selected as correct");
          activate("add");
          setupScreens("add");
          alert.showAndWait();
          return;
        } else {
          answer = rightTF.getText();
        }

        Question newQuestion =
            new Question(question.getText(), choiceArray, image.getText(), topic.getText(), answer);
        quizGenerator.addNewQuestion(newQuestion);
      }
    });

    // if cancel button is clicked, go back to root
    cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        System.out.println("Scanner");
        main.setRoot(root);
      }
    });

    pane.setBottom(hbox);
    pane.setAlignment(saveButton, Pos.CENTER_RIGHT);
    pane.setCenter(vbox);
    pane.setAlignment(vbox, Pos.CENTER);
    pane.setMargin(pane.getTop(), insets);
    pane.setMargin(pane.getCenter(), insets);
    pane.setMargin(pane.getBottom(), insets);
  }
  
}
