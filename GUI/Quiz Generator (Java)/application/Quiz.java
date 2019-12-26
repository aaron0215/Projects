/**
 * Filename: Quiz.java 
 * Project: Quiz Generator 
 * Authors: Aaron Zhang, Aurora Shen, Tyler Gu, Yixing
 * Tu Group: A-Team 68
 * 
 * Quiz class represents a created quiz. It stores selected questions.
 * 
 */

package application;

import java.util.List;

/**
 * This quiz class represent a single quiz
 * which contains the score of this quiz, 
 * the question list for this quiz
 * @author Jiawei Gu
 *
 */
public class Quiz {

  private List<Question> quizQuestion;  // the list of questions in this quiz
  private double score;  // user's score

  Quiz(List<Question> questions) {
    this.quizQuestion = questions;
    this.score = 0;
  }

  /**
   * getter method for list of questions
   * @return
   */
  public List<Question> getQuizQuestion() {
    return quizQuestion;
  }

  /**
   * increment the score 1 point up in case of correct answer
   */
  public void pointIncrement() {
    score++;
  }

  /**
   * Convert score to percentage and return
   * @return
   */
  public double getScore() {
    return (score / quizQuestion.size()) * 100;
  }
  
  /**
   * returns the score to represent the number of questions that got correct
   * @return
   */
  public int getCorrect() {
    return (int) score;
  }
}
