package com.dictionary.service;


import com.dictionary.models.Quiz;

public interface QuizService {

    Quiz generateQuiz(); // generate quiz

    Quiz findQuiz(String quizId);

    Quiz finishQuiz(String quizId, Quiz quiz); // update score of the finished quiz

    void deleteQuiz(String quizId);

}
