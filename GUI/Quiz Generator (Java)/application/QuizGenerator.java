/**
 * Filename: QuizGenerator.java 
 * Project: Quiz Generator 
 * Authors: Aaron Zhang, Aurora Shen, Tyler Gu,
 * Yixing Tu 
 * Group: A-Team 68
 *
 * QuizGenerator class is the main driver class to generate a quiz with given number of questions
 * and topic. It reads questions from JSON file and can add new question to JSON file as well.
 *
 */

package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *  This is the interface for QuizGenerator
 *  It can add questions from a json file of specific format
 *  It can generate quiz based on the topic and desired amount
 *  It can save the current questions to a json file
 *  Can add single question to the question bank
 * @author Jiawei Gu
 *
 */
public class QuizGenerator implements GeneratorInterface {

  private Quiz quiz;  // current quiz
  private ArrayList<Question> questionBank;  // all the questions that we have
  private Set<String> topicList;  // set of topics in question bank
  
  /**
   * non-param constructor
   */
  QuizGenerator(){
    questionBank = new ArrayList<Question>();
    topicList = new HashSet<String>();
  }
  
  /**
   * This reads questions from a json file
   * that we get from the parameter
   * @param filePath JSON file's path
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ParseException
   */
  @Override
  public void addQuestionFromFile(String filePath) 
      throws FileNotFoundException, IOException, ParseException {
    
    Object input = new JSONParser().parse(new FileReader(filePath));
    JSONObject jo = (JSONObject) input; // Typecast
    // Get question array
    JSONArray questionArray = (JSONArray) jo.get("questionArray");  // JSONArray for questionArray
    // Iterate JSONArray
    Iterator itr = questionArray.iterator();  // iterator for questionArray
    Question q;

    // iterate through the question array for every question
    while (itr.hasNext()) {
      JSONObject obj = (JSONObject) itr.next();
      String questionText = (String) obj.get("questionText");
      String topic = (String) obj.get("topic");
      String imagePath = (String) obj.get("image");
      // Read through choices
      JSONArray choiceArray = (JSONArray) obj.get("choiceArray");
      String[] choices = new String[choiceArray.size()];
      String correctAnswer = "";  // correct answer is set to empty initially
      int index = 0;
      for (int i = 0; i < choiceArray.size(); i++) {
        JSONObject currChoice = (JSONObject) choiceArray.get(i);
        choices[i] = (String) currChoice.get("choice");
        if (((String) currChoice.get("isCorrect")).equals("T")) {
          correctAnswer = choices[i];
        }
      }
      if (imagePath.equals("none")) {
        // call corresponding question constructor
        q = new Question(questionText, choices, topic, correctAnswer);
      } else {
        // call corresponding question constructor
        q = new Question(questionText, choices, imagePath, topic, correctAnswer);
      }
      topicList.add(topic);
      questionBank.add(q);
    }
  }

  /**
   * Create a Quiz object with questions with selected topic and given amount
   * @param topic the topic of the quiz to generate
   * @param amount is number of questions to put into the quiz
   */
  @Override
  public void generateQuiz(List<String> topics, int amount) {
    ArrayList<Question> quizQuestions = new ArrayList<>();
    for (Question question : questionBank) {
      if (topics.contains(question.getTopic())) {
        quizQuestions.add(question);
      }
    }
    Collections.shuffle(quizQuestions);  // shuffle topic array to get random result
    
    // put desired amount of questions into quiz
    quiz = new Quiz(quizQuestions.subList(0, amount));
  }

  /**
   * convert current quiz bank to a json file and save it to a specific filePath
   * 
   * @param filePath the filePath we want to save to
   */
  @Override
  public void save(String filePath) throws IOException {
    JSONObject quiz = new JSONObject(); // the entire quiz JSONObject
    JSONArray questionArray = new JSONArray();

    // generate each question to JSONObject and add to question array
    for (Question question : questionBank) {
      JSONObject jsonQuestion = new JSONObject();
      jsonQuestion.put("meta-data", "unused");
      jsonQuestion.put("questionText", question.getQuestion());
      jsonQuestion.put("topic", question.getTopic());
      jsonQuestion.put("image", question.getImage());
      JSONArray choiceArray = new JSONArray();

      // create choice array for this question
      for (String choice : question.getChoices()) {
        JSONObject choiceComb = new JSONObject();
        if (choice.equals(question.getCorrect())) {
          choiceComb.put("isCorrect", "T");
        } else {
          choiceComb.put("isCorrect", "F");
        }
        choiceComb.put("choice", choice);
        choiceArray.add(choiceComb);
      }
      jsonQuestion.put("choiceArray", choiceArray);
      questionArray.add(jsonQuestion); // add this question to JSONArray
    }

    quiz.put("questionArray", questionArray); // add question array to the quiz object
    FileWriter outFile = new FileWriter(filePath); // open file write according to the parameter
    outFile.write(quiz.toJSONString());
    outFile.flush();
  }

  /**
   * add a new question to the question bank
   * @param newQuestion
   */
  @Override
  public void addNewQuestion(Question newQuestion) {
    questionBank.add(newQuestion);
    topicList.add(newQuestion.topic);
  }

  /**
   * getter for quiz
   * @return current quiz
   */
  @Override
  public Quiz getQuiz() {
    return quiz;
  }
  
  /**
   * getter for topic list
   * @return current topic list
   */
  @Override
  public Set<String> getTopicList() {
    return topicList;
  }
  
  /**
   * This method returns number of questions in a
   * specific topic
   * @param topic is the topic that we want to find
   * @return number of questions
   */
  @Override
  public int getNumberOfQuestionsInTopic(String topic) {
    if (!topicList.contains(topic)) {
      return 0;  // if topic is not in the set, simply return 0
    }
    else {
      int number = 0;
      for (Question question : questionBank) {
        if (question.getTopic().equals(topic)) {
          number++;
        }
      }
      return number;
    }
  }
  
  /**
   * getter for question bank
   * @return current question bank
   */
  @Override
  public List<Question> getQuestionBank() {
    return questionBank;
  }
  
  /**
   * getter for quiz's score
   * @return
   */
  public double getScore() {
    return quiz.getScore();
  }
}
