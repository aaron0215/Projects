/**
 * Filename: GeneratorADT.java 
 * Project: Quiz Generator 
 * Authors: Aaron Zhang, Aurora Shen, Tyler Gu,
 * Yixing Tu 
 * Group: A-Team 68
 * 
 */
package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.json.simple.parser.ParseException;

/**
 * 
 *  This is the interface for QuizGenerator
 *  It can add questions from a json file of specific format
 *  It can generate quiz based on the topic and desired amount
 *  It can save the current questions to a json file
 *  Can add single question to the question bank
 *  Use ListADT java.io
 */
public interface GeneratorInterface {
  
  /**
   * This reads questions from a json file
   * that we get from the parameter
   * @param filePath JSON file's path
   * @throws FileNotFoundException if the file is not found
   * @throws IOException if any read error
   * @throws ParseException
   */
  public void addQuestionFromFile(String filePath) throws FileNotFoundException, IOException, ParseException;
  
  /**
   * Create a Quiz object with questions with selected topic and given amount
   * @param topic the topic of the quiz to generate
   * @param amount is number of questions to put into the quiz
   */
  public void generateQuiz(List<String> topic, int amount);
  
  /**
   * convert current quiz bank to a json file and save it to a specific filePath
   * 
   * @param filePath the filePath we want to save to
   */
  public void save(String filePath) throws IOException;
  
  /**
   * add a new question to the question bank
   * @param newQuestion
   */
  public void addNewQuestion(Question newQuestion);
  
  /**
   * getter for quiz
   * @return current quiz
   */
  public Quiz getQuiz();
  
  /**
   * getter for topic list
   * @return current topic list
   */
  public Set<String> getTopicList();
  
  /**
   * This method returns number of questions in a
   * specific topic
   * @param topic is the topic that we want to find
   * @return number of questions
   */
  public int getNumberOfQuestionsInTopic(String topic);
  
  /**
   * getter for question bank
   * @return current question bank
   */
  public List<Question> getQuestionBank();
}
