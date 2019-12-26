/**
 * Filename: UserInterface.java Project: Quiz Generator 
 * Authors: Aaron Zhang, Aurora Shen, Tyler Gu, Yixing Tu 
 * Group: A-Team 68
 * 
 * UserInterface class is GUI class for this project.
 * 
 */

package application;

import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.json.simple.parser.ParseException;

/**
 * The main GUI class
 * 
 * @author Authors: Aaron Zhang, Aurora Shen, Tyler Gu, Yixing Tu
 */
public class Main extends Application {
  protected static QuizGenerator quizGenerator = new QuizGenerator(); // Instance of generator
  protected static boolean needQuit = false; // boolean variable to detect if user wants to quit
  protected static Stage stage; // Primary stage
  protected static HashMap<String, BorderPane> screenMap = new HashMap<>(); // Map stores all screens
  protected static Scene main; // Scene to display different panes
  protected static BorderPane root; // the main menu
  protected static Insets insets = new Insets(10); // Insets used for formatting
  protected static List<String> filesOpened = new ArrayList<>(); // Record files have been read already
  protected static boolean saved = true; // boolean variable to detect if all changes have been saved properly
  protected static ToggleGroup answergroup;

  /**
   * Driver
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    try {
      this.stage = primaryStage;
      primaryStage.setTitle("Quiz Generator");
      root = new BorderPane();
      main = new Scene(root, 800, 600);

      Text title = new Text("Quiz Generator");
      title.setFont(Font.font("Verdana", FontWeight.BOLD, 50));

      root.setTop(title);

      Insets inset = new Insets(30);
      root.setMargin(root.getTop(), inset);
      root.setAlignment(title, Pos.CENTER);
      root.setCenter(this.setUpRootScreen());

      Button exitButton = new Button("Exit");
      exitButton.setPrefSize(150, 60);

      HBox hbox = new HBox();
      hbox.getChildren().add(exitButton);
      hbox.setAlignment(Pos.TOP_CENTER);
      hbox.setPadding(new Insets(30));
      root.setBottom(hbox);
      root.setAlignment(exitButton, Pos.CENTER);

      /**
       * Set action on exit button. Other buttons will be set in separate method
       */
      exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent me) {
          activate("exit"); // call activate method to set scene
          setupScreens("exit");
        }
      });

      // Initialize all screens
      initializeScreens();

      main.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(main);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * Launcher
   * 
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * This method sets up the root screen
   * 
   * @return a hbox of all components needed in root screen
   */
  public HBox setUpRootScreen() {
    HBox hbox = new HBox();
    Button addButton = new Button("Add Question"); // add button for "add question" screen
    Button loadButton = new Button("Load Question"); // button for "load question" screen
    Button saveButton = new Button("Save"); // button for "save" screen
    Button quizButton = new Button("Take Quiz");
    // set preferred size
    addButton.setPrefSize(150, 60);
    loadButton.setPrefSize(150, 60);
    saveButton.setPrefSize(150, 60);
    quizButton.setPrefSize(150, 60);
    // add buttons to hbox
    hbox.getChildren().addAll(addButton, loadButton, quizButton, saveButton);
    hbox.setAlignment(Pos.CENTER);
    hbox.setSpacing(10);

    // EventHandlers
    addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        activate("add"); // Switch to adding screen
        setupScreens("add"); // Set up
      }
    });

    loadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        activate("beforeLoading"); // Switch to the screen where user enters file to read from
        setupScreens("beforeLoading");
      }
    });

    quizButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        activate("load1"); // Switch to save screen
        setupScreens("load1"); // Set up
      }
    });

    saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        activate("save"); // Switch to save screen
        setupScreens("save"); // Set up
      }
    });

    return hbox;
  }

  /**
   * This method initializes all screens that we need
   */
  public void initializeScreens() {
    String[] screenNames = {"add", "load1", "load2", "next", "save", "exit", "beforeLoading"};
    for (String name : screenNames) {
      this.addScreen(name);
    }
  }

  /**
   * Set up screens according to user input
   * 
   * @param name is the name of screen which user is calling
   */
  public static void setupScreens(String name) {
    switch (name) {
      case "add":
        AddQuestionScreen.setUpAddScreen(screenMap.get(name));
        break;
      case "load1":
        AddQuizScreen.setUpGenerateQuizScreen(screenMap.get(name));
        break;
      case "load2":
        AddQuizScreen.quizScreen(screenMap.get(name));
        break;
      case "next":
        AddQuizScreen.setUpNextScreen(screenMap.get(name));
        break;
      case "save":
        AddSaveScreen.setUpSaveScreen(screenMap.get(name));
        break;
      case "exit":
        AddExitScreen.setUpExitScreen(screenMap.get(name));
        break;
      case "beforeLoading":
        AddLoadScreen.setUpBeforeLoadingScreen(screenMap.get(name));
        break;
    }

  }
  
  /**
   * Generate a border pane and add it into map
   * 
   * @param name
   */
  public void addScreen(String name) {
    BorderPane pane = new BorderPane();
    screenMap.put(name, pane);
  }

  /**
   * This method changes scene to desired pane
   * 
   * @param name
   */
  protected static void activate(String name) {
    main.setRoot(screenMap.get(name));
  }

}
