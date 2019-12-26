/**
 * Filename: Question.java 
 * Project: Quiz Generator 
 * Authors: Aaron Zhang, Aurora Shen, Tyler Gu,
 * Yixing Tu 
 * Group: A-Team 68
 * 
 * Question class presents a single question object.
 * 
 */

package application;

/**
 * This class represents a single question in quiz or question bank
 * It contains text, image, choiceArray, topic, correctAnswer
 *
 */
public class Question {
  public String questionText;    // the text for this question
  public String imageFile;       // the image path
  public String[] choiceArray;   // the array for choice text
  public String topic;           // topic of this question
  private String correctAnswer;  // correct answer among the choices

  // Constructor with image input
  public Question(String questionText, String[] choiceArray, String imagePath, String topic,
      String answer) {
    this.questionText = questionText;
    this.choiceArray = choiceArray;
    this.topic = topic;
    this.imageFile = imagePath;
    this.correctAnswer = answer;
  }

  // Constructor without image input
  public Question(String questionText, String[] choiceArray, String topic, String answer) {
    this(questionText, choiceArray, "none", topic, answer);
  }

  /**
   * getter for question text
   * @return
   */
  public String getQuestion() {
    return questionText;
  }

  /**
   * Return image path
   * @return
   */
  public String getImage() {
    return imageFile;
  }

  /**
   * return topic in string
   * @return
   */
  public String getTopic() {
    return topic;
  }

  /**
   * return the array of choices
   * @return
   */
  public String[] getChoices() {
    return choiceArray;
  }

  /**
   * return the correct answer among choices
   * @return
   */
  public String getCorrect() {
    return correctAnswer;
  }

}
