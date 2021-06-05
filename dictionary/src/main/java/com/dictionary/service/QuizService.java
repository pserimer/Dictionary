// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.service;

import com.dictionary.models.Quiz;

public interface QuizService {

    Quiz generateQuiz(); // generate quiz

    Quiz findQuiz(Long quizId);

    Quiz finishQuiz(Long quizId, Quiz quiz); // update score of the finished quiz

    void deleteQuiz(Long quizId);

}
