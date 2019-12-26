/**
 * Filename: QuizGeneratorTest.java 
 * Project: Quiz Generator 
 * Authors: Aaron Zhang, Aurora Shen, Tyler Gu,
 * Yixing Tu 
 * Group: A-Team 68
 * 
 * JUnit test to test QuizGenerator.java functions
 * 
 */

package application;

import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test to test QuizGenerator class
 *
 */
public class QuizGeneratorTest {
  QuizGenerator qg_000; // quiz generator with json file "question.json"
  QuizGenerator qg_001; // quiz generator with json file "question_001.json"
  QuizGenerator qg_002; // quiz generator with json file "question_002.json"
  QuizGenerator qg_003; // quiz generator with json file "question_003.json"

  /**
   * set up
   * 
   * @throws Exception
   */
  @Before
  public void setup() throws Exception {
    qg_000 = new QuizGenerator();
    qg_001 = new QuizGenerator();
    qg_002 = new QuizGenerator();
    qg_003 = new QuizGenerator();
    qg_000.addQuestionFromFile("question.json");
    qg_001.addQuestionFromFile("questions_001.json");
    qg_002.addQuestionFromFile("questions_002.json");
    qg_003.addQuestionFromFile("questions_003.json");
  }

  /**
   * tear down
   * 
   * @throws Exception
   */
  @After
  public void tearDown() throws Exception {
    qg_000 = null;
    qg_001 = null;
    qg_002 = null;
    qg_003 = null;
  }

  /**
   * This test checks if QuizGenerator gets the corrct question bank out of json file
   */
  @Test
  public void test00_question_bank_size() {
    try {
      if (qg_000.getQuestionBank().size() != 2) {
        fail("The size of question bank should be 2, return value is: " 
            + qg_000.getQuestionBank().size());
      }
    } catch (Exception e) {
      fail("Unexpected exception: " + e.getStackTrace());
    }
  }

  /**
   * This test generates quiz with topic which will result only in quiz with only one question
   * @throws Exception
   */
  @Test
  public void test01_generator_quiz_with_one_question() throws Exception {
    List<String> topicList = new ArrayList<String>();
    topicList.add("linux");
    qg_000.generateQuiz(topicList, 1);
    Quiz quiz = qg_000.getQuiz();
    if (!quiz.getQuizQuestion().get(0).getTopic().equals("linux")) {
      fail("generate quiz function fails, topic does not match. \n" + "Excepted: linux, return: "
          + quiz.getQuizQuestion().get(0).getTopic());
    }
  }
  
  /**
   * This tests getNumberOfQuestionsInTopic with the topic that does not exist
   * in the question bank
   * 
   */
  @Test
  public void test_02_getNumberOfQuestionsInTopic_DNE() {
    try{
      if ( qg_001.getNumberOfQuestionsInTopic("none") != 0 ) {
        fail("getNumberOfQuestionsInTopic should return 0, but returns "
            + qg_001.getNumberOfQuestionsInTopic("none"));
      }
      
    } catch (Exception e) {
      fail("Unexpected exception: " + e.getStackTrace());
    }
  }
  
  /**
   * This tests getNumberOfQuestionsInTopic with the questions_002.json file
   * Expect: 2
   */
  @Test
  public void test_03_getNumberOfQuestionsInTopic_002_json() {
    try {
      if ( qg_002.getNumberOfQuestionsInTopic("tree") != 2 ) {
        fail("getNumberOfQuestionsInTopic should return 2, but returns " 
            + qg_002.getNumberOfQuestionsInTopic("none"));
      }
    }
    catch (Exception e) {
      fail("Unexpected exception: " + e.getStackTrace());
    }  
  }
  
  /**
   * This tests getTopicList with questions_002.json
   */
  @Test
  public void test_04_getTopicList_002_json() {
    try {
      if ( !qg_002.getTopicList().contains("linux") 
          || !qg_002.getTopicList().contains("hash table") 
          || !qg_002.getTopicList().contains("tree")) {
        fail("getToplicList does not return the correct list of topics");
      }
    }catch (Exception e) {
      fail("Unexpected exception: " + e.getStackTrace());
    }  
  }
  
  /**
   * This tests getTopicList with questions_003.json
   */
  @Test
  public void test_05_getTopicList_003_json() {
    try {
      if ( !qg_003.getTopicList().contains("set") 
          || !qg_003.getTopicList().contains("performance") 
          || !qg_003.getTopicList().contains("tree")
          || !qg_003.getTopicList().contains("graph")) {
        fail("getToplicList does not return the correct list of topics");
      }
    }catch (Exception e) {
      fail("Unexpected exception: " + e.getStackTrace());
    }  
  }
  
  /**
   * This tests if add one question and save work properly
   */
  @Test
  public void test_06_add_one_question_and_save() {
    try {
      Question newQuestion = new Question(
          "The _____ command will copy files from a local repository to a remote repository.",
          new String[]{"1", "2", "3", "4", "5"},
          "git",
          "git push"
          );
      qg_002.addNewQuestion(newQuestion);
      qg_002.save("test.json");
      QuizGenerator test = new QuizGenerator();
      test.addQuestionFromFile("test.json");
      if (test.getQuestionBank().size() != 5) {
        fail("Expect the new json file to have 5 questions, but returns: " 
            + test.getQuestionBank().size());
      }
    } catch (Exception e) {
      fail("Unexpected exception: " + e.toString());
    }  
  }
  
  /**
   * This checks QuizGenerator's function of generating quiz with multiple topics
   */
  @Test
  public void test_08_generate_quiz_with_two_topics() {
    try {
      List<String> topicList = new ArrayList<String>();
      topicList.add("linux");
      topicList.add("tree");
      qg_002.generateQuiz(topicList, 3);
      Quiz quiz = qg_002.getQuiz();
      if ( quiz.getQuizQuestion().size() != 3 ) {
        fail("generate quiz function fails, number does not match. \n" + "Excepted: 3, return: "
            + quiz.getQuizQuestion().size());
      }
    }catch (Exception e) {
      fail("Unexpected exception: " + e.toString());
    }
  }
}