// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.service;

import com.dictionary.models.Question;

import java.util.Set;

public interface QuestionService {

    Set<Question> createQuestionsForEnglish();

    Set<Question> createQuestionsForTurkish();

}
