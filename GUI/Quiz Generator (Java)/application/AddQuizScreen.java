/**
 * Filename: UserInterface.java Project: Quiz Generator 
 * Authors: Aaron Zhang, Aurora Shen, Tyler Gu, Yixing Tu 
 * Group: A-Team 68
 * 
 * UserInterface class is GUI class for this project.
 * 
 */

package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.simple.parser.ParseException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Class represents screen generates quiz
 */
public class AddQuizScreen extends Main {
  static int count; // Count how many questions have been answered
  
  /**
   * Load1 screen asks user the desired topic and amount of questions
   * 
   * @param pane is the pane we are setting up
   */
  public static void setUpGenerateQuizScreen(BorderPane pane) {
    if (quizGenerator.getQuestionBank().isEmpty()) {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Empty Quesiton Bank");
      alert.setHeaderText("You must load questions using files or add manually");
      alert.showAndWait();
      main.setRoot(root);
      return;
    }
    VBox vbox = new VBox();
    BorderPane currScreen = pane;
    Text text = new Text("Load question");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    currScreen.setTop(text);

    // Read all topics as an array
    Object[] topicArray = quizGenerator.getTopicList().toArray();
    Arrays.sort(topicArray); // Sort topics by alphabetical order
    // Number of all questions with any topic
    int totalNum = quizGenerator.getQuestionBank().size();

    // Push array into observable list which is easier to use in combo box
    ObservableList<String> topics = FXCollections.observableArrayList((String) topicArray[0]);
    if (topicArray.length > 1) {
      for (int i = 1; i < topicArray.length; i++) {
        topics.add((String) topicArray[i]);
      }
    }

    // Check box lists all topics
    VBox topicBox = new VBox(); // hbox for topic prompt
    ArrayList<String> selectedTopic = new ArrayList<String>();
    for (int i = 0; i < topics.size(); i++) {
      String st = topics.get(i);
      CheckBox c = new CheckBox(st);
      c.selectedProperty().addListener(new ChangeListener<Boolean>() {
        public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val,
            Boolean new_val) {
          if (selectedTopic.contains(st)) {
            selectedTopic.remove(st);
          } else {
            selectedTopic.add(st);
          }
        }
      });
      topicBox.getChildren().add(c);
    }
    topicBox.setAlignment(Pos.CENTER);

    // hbox for number of question prompt
    HBox numberQuestionHBox = new HBox();
    TextField questionNum = new TextField();
    numberQuestionHBox.getChildren().addAll(new Text("# of Questions: "), questionNum);
    numberQuestionHBox.setAlignment(Pos.CENTER);

    vbox.getChildren()
        .add(new Text("Total number of questions with all topics avaliable: " + totalNum));
    vbox.getChildren().add(topicBox);
    vbox.getChildren().add(numberQuestionHBox);


    vbox.setSpacing(10);
    vbox.setAlignment(Pos.CENTER);
    currScreen.setCenter(vbox);

    HBox buttons = new HBox(); // Buttons
    Button backButton = new Button("Cancel");
    backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        main.setRoot(root); // Cancel button brings user back to root screen
      }
    });

    Button loadButton = new Button("Start"); // Start the quiz
    loadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        int amountLimit = 0;
        for (int i = 0; i < selectedTopic.size(); i++) {
          amountLimit += quizGenerator.getNumberOfQuestionsInTopic(selectedTopic.get(i));
        }
        try {
          if (questionNum.getText().isEmpty()) { // catch empty input
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("You must enter an amount of questions");
            alert.showAndWait();
          } else if (Integer.parseInt(questionNum.getText()) <= 0) { // catch negative input
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Invalid Number");
            alert.setHeaderText("Number of questions must be a positive number!");
            alert.showAndWait();
            questionNum.clear();
          } else {
            int requestNum = Integer.parseInt(questionNum.getText());
            if (requestNum > amountLimit) { // Requested an amount is greater than the capacity
              // Warn user and still give questions with maximum capacity
              Alert alert = new Alert(AlertType.INFORMATION);
              alert.setTitle("Notice");
              alert.setHeaderText("Excessive question amount");
              alert.setContentText("You entered an amount is more than questions we have,\n"
                  + "you will only be able to take " + amountLimit + " question(s) this time");
              alert.showAndWait();
              requestNum = amountLimit;
            }
            
            try{
              generateQuiz(selectedTopic, requestNum);
            }
            catch (IOException | ParseException e) {
              Alert alert = new Alert(AlertType.INFORMATION);
              alert.setTitle("Alert");
              alert.setHeaderText("Unexcepted exception occured");
              alert.showAndWait();
            }
            
            // Quiz is ready to go
            setupScreens("load2");
            activate("load2");
            count = 0; // Set count of answered questions to be 0
          }
        } catch (NumberFormatException e) { // Catch invalid input (non-digit)
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Alert");
          alert.setHeaderText("You must enter a number");
          alert.showAndWait();
          questionNum.clear();
        }
      }
    });

    buttons.getChildren().addAll(loadButton, backButton);
    buttons.setAlignment(Pos.CENTER_RIGHT);
    buttons.setSpacing(10);
    currScreen.setBottom(buttons);
    currScreen.setAlignment(buttons, Pos.CENTER_RIGHT);
    pane.setMargin(pane.getTop(), insets);
    pane.setMargin(pane.getCenter(), insets);
    pane.setMargin(pane.getBottom(), insets);
  }
  
  /**
   * Load2 screen is where user does the quiz questions
   * 
   * @param pane is the pane we are setting up
   */
  public static void quizScreen(BorderPane pane) {
    count = 0;

    // List of selected questions
    List<Question> quizQuestion = quizGenerator.getQuiz().getQuizQuestion();

    // Toggle group used to detect user's choice
    answergroup = showQuestion(pane, quizQuestion);

    HBox hbox = new HBox();

    Button submit = new Button("Submit"); // Finish quiz immediately
    Button next = new Button("Next"); // Go to next question
    hbox.getChildren().addAll(submit, next);
    hbox.setAlignment(Pos.CENTER_RIGHT);
    pane.setBottom(hbox);
    hbox.setSpacing(10);

    // Submit and next question button doesn't apply if user didn't choose anything

    // Finish quiz and jump to result page
    submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        RadioButton selectedRadioButton = (RadioButton) answergroup.getSelectedToggle();
        if (selectedRadioButton != null) { // Make sure user chose one answer
          String toogleGroupValue = selectedRadioButton.getText();
          grade(toogleGroupValue, quizQuestion.get(count));
          setupScreens("next");
          activate("next");
        } else { // Warn user that it's required to select one choice
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Alert");
          alert.setHeaderText("You must select one choice before move on");
          alert.showAndWait();
        }
      }
    });

    // Move on to next questions. Most detections are similar with submit button
    // Once all questions have been answered it jumps to result page
    next.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        RadioButton selectedRadioButton = (RadioButton) answergroup.getSelectedToggle();
        if (selectedRadioButton != null) {
          String toogleGroupValue = selectedRadioButton.getText();
          grade(toogleGroupValue, quizQuestion.get(count));
          if (count == quizQuestion.size() - 1) {
            setupScreens("next");
            activate("next");
          } else {
            count++;
            answergroup = showQuestion(pane, quizQuestion);
          }
        } else {
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Alert");
          alert.setHeaderText("You must select one choice before move on");
          alert.showAndWait();
        }
      }
    });

    pane.setMargin(pane.getBottom(), insets);

  }
  
  /**
   * Private helper to grade each question
   * 
   * @param choice   is the choice user chose
   * @param question is the current question
   */
  private static void grade(String choice, Question question) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Result");
    if (choice.equals(question.getCorrect())) {
      alert.setHeaderText("Correct!"); // Shows user if the current choice is correct
      quizGenerator.getQuiz().pointIncrement();
    } else {
      alert.setHeaderText("Incorrect!");
    }
    alert.setContentText("Click on the button below to move on when you are ready!");
    alert.showAndWait();
  }
  
  /**
   * Next screen means the result right after the quiz
   * 
   * @param pane is the pane we are working on
   */
  public static void setUpNextScreen(BorderPane pane) {
    Text text = new Text("Result");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    pane.setTop(text);
    VBox vbox = new VBox();
    // Add number of correctly answered questions
    vbox.getChildren().add(new Text("Correct: " + quizGenerator.getQuiz().getCorrect()));
    // Add number of total answered questions
    vbox.getChildren().add(new Text("Questions Answered: " + (count + 1)));
    // Show score in percentage
    vbox.getChildren().addAll(new Text("Score:  "
        + Double.toString(Math.ceil(quizGenerator.getQuiz().getScore())) + " %"));
    pane.setCenter(vbox);
    pane.setAlignment(vbox, Pos.CENTER);
    pane.setMargin(vbox, insets);

    // Choices
    HBox resultChoice = new HBox();
    // Change topic and numbers
    Button changeSetting = new Button("Change setting");
    // Try the same quiz again
    Button tryAgain = new Button("Try Again");
    // Go back to root screen
    Button quit = new Button("Finish");
    resultChoice.getChildren().addAll(changeSetting, tryAgain, quit);

    changeSetting.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        setupScreens("load1");
        activate("load1");
      }
    });

    tryAgain.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        setupScreens("load2");
        activate("load2");
      }
    });

    quit.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        main.setRoot(root);
      }
    });

    resultChoice.setAlignment(Pos.CENTER_RIGHT);
    pane.setBottom(resultChoice);
    resultChoice.setSpacing(10);
    pane.setMargin(pane.getTop(), insets);
    pane.setMargin(pane.getCenter(), insets);
    pane.setMargin(pane.getBottom(), insets);
  }
  
  /**
   * Method to show question such that we can generate new questions easier.
   * 
   * @param pane
   * @param quizQuestion
   * @return toggle group of answers
   */
  public static ToggleGroup showQuestion(BorderPane pane, List<Question> quizQuestion) {

    BorderPane currentScreen = pane;
    Text text = new Text("Quiz Question #" + (count + 1));
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

    // Record quiz progress
    VBox recordBox = new VBox();
    recordBox.getChildren().add(text);
    recordBox.getChildren().add(new Text("Answered: " + count));
    recordBox.getChildren().add(new Text("Total: " + quizQuestion.size()));
    recordBox.setAlignment(Pos.CENTER_LEFT);
    currentScreen.setTop(recordBox);

    HBox hbox = new HBox();
    Text questionText = new Text(10, 20, quizQuestion.get(count).getQuestion());
    questionText.setWrappingWidth(500);

    VBox vbox = new VBox();
    vbox.getChildren().add(questionText);
    String[] choices = quizQuestion.get(count).getChoices();

    // Image part
    String imagePath = quizQuestion.get(count).getImage();
    ImageView image;
    try {
      if (!imagePath.equals("none")) {
        image = new ImageView("images/" + imagePath);
      } else { // leave an empty frame
        image = new ImageView();
      }
    } catch (IllegalArgumentException e) { // Cannot open image. Inform user and shows blank
      image = new ImageView("images/invalidImage.jpg");
    }
    image.setFitHeight(200);
    image.setFitWidth(200);
    vbox.getChildren().add(image);

    ToggleGroup answergroup = new ToggleGroup();
    RadioButton answerbutton = new RadioButton();
    answerbutton.setToggleGroup(answergroup);

    hbox.setAlignment(Pos.CENTER); // Generate choices
    for (int i = 0; i < choices.length; i++) {
      hbox = new HBox();
      RadioButton button = new RadioButton(choices[i]);
      button.setToggleGroup(answergroup);
      vbox.getChildren().add(button);
    }
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(10);

    currentScreen.setCenter(vbox);

    pane.setMargin(pane.getTop(), insets);
    pane.setMargin(pane.getCenter(), insets);

    return answergroup;
  }
  
  /**
   * Generate a quiz with given topic and the amount of questions
   * 
   * @param topic  is the topic user chooses
   * @param amount is the amount of questions user wants
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ParseException
   */
  public static void generateQuiz(List<String> topic, int amount)
      throws FileNotFoundException, IOException, ParseException {
    quizGenerator.generateQuiz(topic, amount);
  }
  
}
